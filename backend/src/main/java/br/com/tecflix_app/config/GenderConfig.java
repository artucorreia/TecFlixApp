package br.com.tecflix_app.config;

import br.com.tecflix_app.modules.gender.application.gateways.GenderRepositoryGateway;
import br.com.tecflix_app.modules.gender.application.usecases.FindGenderByIdUseCase;
import br.com.tecflix_app.modules.gender.application.usecases.FindGenderByIdUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GenderConfig {
  @Bean
  public FindGenderByIdUseCase findGenderByIdUseCase(
      GenderRepositoryGateway genderRepositoryGateway) {
    return new FindGenderByIdUseCaseImpl(genderRepositoryGateway);
  }
}
