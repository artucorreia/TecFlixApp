package br.com.tecflix_app.modules.auth.application.usecases;

import br.com.tecflix_app.config.provider.ConfigProvider;
import br.com.tecflix_app.modules.auth.application.gateways.TokenGateway;

import java.util.UUID;
import java.util.logging.Logger;

public class ValidateTokenUseCaseImpl implements ValidateTokenUseCase {
  private final Logger LOGGER = Logger.getLogger(ValidateTokenUseCaseImpl.class.getName());
  private final ConfigProvider configProvider;
  private final TokenGateway tokenGateway;

  public ValidateTokenUseCaseImpl(ConfigProvider configProvider, TokenGateway tokenGateway) {
    this.configProvider = configProvider;
    this.tokenGateway = tokenGateway;
  }

  @Override
  public UUID execute(String token) {
    LOGGER.info("Validating JWT token");
    String secret = configProvider.getTokenSecret();
    String issuer = configProvider.getTokenIssuer();
    try {
      return tokenGateway.validate(secret, issuer, token);
    } catch (Exception e) {
      LOGGER.warning("Erro na validação do token: " + e.getMessage());
    }
    return null;
  }
}
