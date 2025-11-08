package br.com.tecflix_app.modules.auth.application.usecases;

import br.com.tecflix_app.config.provider.ConfigProvider;
import br.com.tecflix_app.modules.auth.application.domain.entity.TokenJwt;
import br.com.tecflix_app.modules.auth.application.domain.exception.JwtCreationTokenException;
import br.com.tecflix_app.modules.auth.application.gateways.TokenGateway;

import java.time.Duration;
import java.util.UUID;
import java.util.logging.Logger;

public class GenerateTokenUseCaseImpl implements GenerateTokenUseCase {
  private final Logger LOGGER = Logger.getLogger(GenerateTokenUseCaseImpl.class.getName());
  private final ConfigProvider configProvider;
  private final TokenGateway tokenGateway;

  public GenerateTokenUseCaseImpl(ConfigProvider configProvider, TokenGateway tokenGateway) {
    this.configProvider = configProvider;
    this.tokenGateway = tokenGateway;
  }

  @Override
  public TokenJwt execute(UUID userId) {
    LOGGER.info("Generating JWT token for user: " + userId);
    Duration tokenDuration = configProvider.getTokenDuration();
    String tokenIssuer = configProvider.getTokenIssuer();
    String tokenSecret = configProvider.getTokenSecret();

    try {
      return tokenGateway.generate(tokenSecret, tokenDuration, tokenIssuer, userId);
    } catch (Exception e) {
      throw new JwtCreationTokenException("Ocorreu um erro durante a geração do token JWT");
    }
  }
}
