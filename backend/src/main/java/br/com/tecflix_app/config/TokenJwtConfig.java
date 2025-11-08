package br.com.tecflix_app.config;

import br.com.tecflix_app.config.provider.ConfigProvider;
import br.com.tecflix_app.modules.auth.application.gateways.TokenGateway;
import br.com.tecflix_app.modules.auth.application.usecases.GenerateTokenUseCase;
import br.com.tecflix_app.modules.auth.application.usecases.GenerateTokenUseCaseImpl;
import br.com.tecflix_app.modules.auth.application.usecases.ValidateTokenUseCase;
import br.com.tecflix_app.modules.auth.application.usecases.ValidateTokenUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TokenJwtConfig {

  @Bean
  public GenerateTokenUseCase generateTokenUseCase(
      ConfigProvider configProvider, TokenGateway tokenGateway) {
    return new GenerateTokenUseCaseImpl(configProvider, tokenGateway);
  }

  @Bean
  public ValidateTokenUseCase validateTokenUseCase(
      ConfigProvider configProvider, TokenGateway tokenGateway) {
    return new ValidateTokenUseCaseImpl(configProvider, tokenGateway);
  }
}
