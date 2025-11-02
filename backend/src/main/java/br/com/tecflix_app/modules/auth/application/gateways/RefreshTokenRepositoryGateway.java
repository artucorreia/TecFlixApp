package br.com.tecflix_app.modules.auth.application.gateways;

import br.com.tecflix_app.modules.auth.application.domain.entity.RefreshToken;

import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenRepositoryGateway {
  Optional<RefreshToken> findByToken(String token);

  void save(RefreshToken refreshToken);

  void deleteByUserId(UUID userId);
}
