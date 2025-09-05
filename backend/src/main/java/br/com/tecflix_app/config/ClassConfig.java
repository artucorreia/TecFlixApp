package br.com.tecflix_app.config;

import br.com.tecflix_app.modules.auth.application.gateways.AuthenticatedUserGateway;
import br.com.tecflix_app.modules.courseClass.application.gateways.ClassRepositoryGateway;
import br.com.tecflix_app.modules.courseClass.application.usecases.CreateClassUseCase;
import br.com.tecflix_app.modules.courseClass.application.usecases.CreateClassUseCaseImpl;
import br.com.tecflix_app.modules.module.application.usecases.FindModuleByIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClassConfig {

  @Bean
  public CreateClassUseCase createClassUseCase(
      ClassRepositoryGateway classRepositoryGateway,
      FindModuleByIdUseCase findModuleByIdUseCase,
      AuthenticatedUserGateway authenticatedUserGateway) {
    return new CreateClassUseCaseImpl(
        classRepositoryGateway, findModuleByIdUseCase, authenticatedUserGateway);
  }
}
