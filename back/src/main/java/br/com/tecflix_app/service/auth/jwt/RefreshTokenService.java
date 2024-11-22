package br.com.tecflix_app.service.auth.jwt;

import java.time.Instant;
import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.tecflix_app.exception.auth.RefreshTokenException;
import br.com.tecflix_app.exception.general.ResourceNotFoundException;
import br.com.tecflix_app.model.RefreshToken;
import br.com.tecflix_app.model.User;
import br.com.tecflix_app.repository.RefreshTokenRepository;
import br.com.tecflix_app.repository.UserRepository;

@Service
public class RefreshTokenService {
    private final Logger LOGGER = Logger.getLogger(RefreshTokenService.class.getName());
    
    private final RefreshTokenRepository repository;
    private final UserRepository userRepository;

    @Value("${security.jwt.token.refresh.duration}")
    private Long duration;

    @Autowired
    public RefreshTokenService(
        RefreshTokenRepository repository,
        UserRepository userRepository
    ) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public RefreshToken create(UUID userId) {
        LOGGER.info("Creating refresh token");
        RefreshToken entity = RefreshToken.builder()
            .id(null)
            .token(UUID.randomUUID().toString())
            .user(getUser(userId))
            .expiresAt(Instant.now().plusMillis(duration))
            .build();
        return repository.save(entity);
    }

    private User getUser(UUID id) {
        LOGGER.info("Finding refresh token owner");
        return userRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Nenhum usuário encontrado para este id")
        );
    }

    public UUID resolve(String token) {
        RefreshToken refreshToken = findByToken(token);
        delete(refreshToken);
        verifyTokenExpiration(refreshToken);
        return refreshToken.getUser().getId();
    }

    private RefreshToken findByToken(String token) {
        LOGGER.info("Finding refresh token");
        return repository.findByToken(token)
            .orElseThrow(
                () -> new ResourceNotFoundException("Token não encontrado")
            );
    }

    private void verifyTokenExpiration(RefreshToken refreshToken) {
        LOGGER.info("Checking refresh token");
        if (refreshToken.getExpiresAt().isBefore(Instant.now())) throw new RefreshTokenException("Token expirado");
    }

    private void delete(RefreshToken refreshToken) {
        LOGGER.info("Deleting old refresh token");
        repository.delete(refreshToken);
    }
}
