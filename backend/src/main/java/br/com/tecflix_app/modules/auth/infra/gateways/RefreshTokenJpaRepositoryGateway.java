package br.com.tecflix_app.modules.auth.infra.gateways;

import br.com.tecflix_app.modules.auth.application.domain.entity.RefreshToken;
import br.com.tecflix_app.modules.auth.application.gateways.RefreshTokenRepositoryGateway;
import br.com.tecflix_app.modules.auth.infra.gateways.mapper.AuthGatewaysMapper;
import br.com.tecflix_app.modules.auth.infra.persistence.RefreshTokenEntity;
import br.com.tecflix_app.modules.auth.infra.persistence.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RefreshTokenJpaRepositoryGateway implements RefreshTokenRepositoryGateway {
  private final RefreshTokenRepository refreshTokenRepository;
  private final AuthGatewaysMapper authGatewaysMapper;

  @Override
  public Optional<RefreshToken> findByToken(String token) {
    Optional<RefreshTokenEntity> refreshTokenEntityOptional = refreshTokenRepository.findByToken(token);
    return refreshTokenEntityOptional.map(authGatewaysMapper::map);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void save(RefreshToken refreshToken) {
    RefreshTokenEntity refreshTokenEntity = authGatewaysMapper.map(refreshToken);
    refreshTokenRepository.save(refreshTokenEntity);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void deleteByUserId(UUID userId) {
    refreshTokenRepository.deleteByUserId(userId);
  }
}
