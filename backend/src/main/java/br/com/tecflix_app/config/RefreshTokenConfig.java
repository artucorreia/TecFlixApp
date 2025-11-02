package br.com.tecflix_app.config;

import br.com.tecflix_app.config.provider.ConfigProvider;
import br.com.tecflix_app.modules.auth.application.gateways.RefreshTokenRepositoryGateway;
import br.com.tecflix_app.modules.auth.application.usecases.CreateRefreshTokenUseCase;
import br.com.tecflix_app.modules.auth.application.usecases.CreateRefreshTokenUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RefreshTokenConfig {
  @Bean
  CreateRefreshTokenUseCase createRefreshTokenUseCase(
      RefreshTokenRepositoryGateway refreshTokenRepositoryGateway, ConfigProvider configProvider) {
    return new CreateRefreshTokenUseCaseImpl(refreshTokenRepositoryGateway, configProvider);
  }
}
