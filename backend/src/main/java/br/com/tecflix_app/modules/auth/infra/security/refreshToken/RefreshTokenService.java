package br.com.tecflix_app.modules.auth.infra.security.refreshToken;

import br.com.tecflix_app.modules.auth.infra.persistence.RefreshTokenEntity;
import br.com.tecflix_app.modules.auth.infra.persistence.RefreshTokenRepository;
import br.com.tecflix_app.modules.user.infra.persistence.UserEntity;
import br.com.tecflix_app.modules.user.infra.persistence.UserRepository;
import br.com.tecflix_app.modules.shared.exception.auth.RefreshTokenException;
import br.com.tecflix_app.modules.shared.exception.general.ResourceNotFoundException;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.logging.Logger;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
  private final Logger LOGGER = Logger.getLogger(RefreshTokenService.class.getName());

  private final RefreshTokenRepository repository;
  private final UserRepository userRepository;

  @Value("${security.jwt.token.refresh.duration}")
  private Duration duration;

  @Transactional(rollbackFor = Exception.class)
  public RefreshTokenEntity create(UUID userId) {
    deleteByUserId(userId);
    LOGGER.info("Creating refresh token");
    RefreshTokenEntity entity =
        RefreshTokenEntity.builder()
            .token(UUID.randomUUID().toString())
            .user(getUser(userId))
            .expiresAt(Instant.now().plus(duration))
            .createdAt(LocalDateTime.now())
            .deleted(false)
            .build();
    return repository.save(entity);
  }

  private UserEntity getUser(UUID id) {
    LOGGER.info("Finding refresh token owner");
    return userRepository
        .findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Nenhum usuário encontrado para este id"));
  }

  public UUID resolve(String token) {
    RefreshTokenEntity refreshTokenEntity = findByToken(token);
    verifyTokenExpiration(refreshTokenEntity);
    return refreshTokenEntity.getUser().getId();
  }

  private RefreshTokenEntity findByToken(String token) {
    LOGGER.info("Finding refresh token");
    return repository
        .findByToken(token)
        .orElseThrow(() -> new ResourceNotFoundException("Token não encontrado"));
  }

  private void verifyTokenExpiration(RefreshTokenEntity refreshTokenEntity) {
    LOGGER.info("Checking refresh token");
    if (refreshTokenEntity.getExpiresAt().isBefore(Instant.now()))
      throw new RefreshTokenException("Token expirado");
  }

  private void deleteByUserId(UUID userId) {
    LOGGER.info("Deleting old user refresh token");
//    repository.deleteByUserId(userId);
  }
}
