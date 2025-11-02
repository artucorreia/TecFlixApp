package br.com.tecflix_app.modules.auth.application.usecases;

import br.com.tecflix_app.config.provider.ConfigProvider;
import br.com.tecflix_app.modules.auth.application.domain.entity.RefreshToken;
import br.com.tecflix_app.modules.auth.application.gateways.RefreshTokenRepositoryGateway;
import br.com.tecflix_app.modules.user.application.domain.entity.User;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.logging.Logger;

public class CreateRefreshTokenUseCaseImpl implements CreateRefreshTokenUseCase {
  private final Logger LOGGER = Logger.getLogger(CreateRefreshTokenUseCaseImpl.class.getName());
  private final RefreshTokenRepositoryGateway refreshTokenRepositoryGateway;
  private final ConfigProvider envConfigProvider;

  public CreateRefreshTokenUseCaseImpl(
      RefreshTokenRepositoryGateway refreshTokenRepositoryGateway,
      ConfigProvider envConfigProvider) {
    this.refreshTokenRepositoryGateway = refreshTokenRepositoryGateway;
    this.envConfigProvider = envConfigProvider;
  }

  @Override
  public String execute(User user) {
    LOGGER.info("Creating a new refresh token to user: " + user.getId());
    refreshTokenRepositoryGateway.deleteByUserId(user.getId());
    RefreshToken refreshToken = new RefreshToken();
    String token = UUID.randomUUID().toString();
    refreshToken.setToken(token);
    refreshToken.setUser(user);
    refreshToken.setExpiresAt(Instant.now().plus(envConfigProvider.getRefreshTokenDuration()));
    refreshToken.setDeleted(false);
    refreshToken.setCreatedAt(LocalDateTime.now());
    refreshTokenRepositoryGateway.save(refreshToken);
    return token;
  }
}
