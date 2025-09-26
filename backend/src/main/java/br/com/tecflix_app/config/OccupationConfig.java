package br.com.tecflix_app.config;

import br.com.tecflix_app.modules.occupation.application.gateways.OccupationRepositoryGateway;
import br.com.tecflix_app.modules.occupation.application.usecases.FindOccupationByIdUseCase;
import br.com.tecflix_app.modules.occupation.application.usecases.FindOccupationByIdUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OccupationConfig {
  @Bean
  public FindOccupationByIdUseCase findOccupationByIdUseCase(
      OccupationRepositoryGateway occupationRepositoryGateway) {
    return new FindOccupationByIdUseCaseImpl(occupationRepositoryGateway);
  }
}
