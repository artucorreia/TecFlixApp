package br.com.tecflix_app.modules.auth.application.usecases;

import br.com.tecflix_app.modules.auth.application.domain.entity.RefreshToken;
import br.com.tecflix_app.modules.auth.application.gateways.RefreshTokenRepositoryGateway;
import br.com.tecflix_app.modules.shared.exception.auth.RefreshTokenException;
import br.com.tecflix_app.modules.shared.exception.general.ResourceNotFoundException;
import br.com.tecflix_app.modules.user.application.domain.entity.User;

import java.time.Instant;
import java.util.logging.Logger;

public class ResolveRefreshTokenUseCaseImpl implements ResolveRefreshTokenUseCase {
  private final Logger LOGGER = Logger.getLogger(ResolveRefreshTokenUseCaseImpl.class.getName());
  private final RefreshTokenRepositoryGateway refreshTokenRepositoryGateway;

  public ResolveRefreshTokenUseCaseImpl(
      RefreshTokenRepositoryGateway refreshTokenRepositoryGateway) {
    this.refreshTokenRepositoryGateway = refreshTokenRepositoryGateway;
  }

  @Override
  public User execute(String token) {
    LOGGER.info("Resolving refresh token: " + token);

    RefreshToken refreshToken =
        refreshTokenRepositoryGateway
            .findByToken(token)
            .orElseThrow(
                () -> new ResourceNotFoundException("Token não encontrado"));

    if (refreshToken.getExpiresAt().isBefore(Instant.now()))
      throw new RefreshTokenException("Token expirado");
    return refreshToken.getUser();
  }
}
