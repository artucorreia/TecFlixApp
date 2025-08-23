package br.com.tecflix_app.config;

import br.com.tecflix_app.modules.user.application.gateways.UserRepositoryGateway;
import br.com.tecflix_app.modules.user.application.usecases.FindUserByIdUseCase;
import br.com.tecflix_app.modules.user.application.usecases.FindUserByIdUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

  @Bean
  public FindUserByIdUseCase findUserByIdUseCase(UserRepositoryGateway userRepositoryGateway) {
    return new FindUserByIdUseCaseImpl(userRepositoryGateway);
  }
}
