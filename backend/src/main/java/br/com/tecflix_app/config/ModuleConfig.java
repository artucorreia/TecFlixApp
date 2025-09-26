package br.com.tecflix_app.config;

import br.com.tecflix_app.modules.auth.application.gateways.AuthenticatedUserGateway;
import br.com.tecflix_app.modules.course.application.usecases.FindCourseByIdUseCase;
import br.com.tecflix_app.modules.module.application.gateways.ModuleRepositoryGateway;
import br.com.tecflix_app.modules.module.application.usecases.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModuleConfig {

  @Bean
  public FindModuleByIdUseCase findModuleByIdUseCase(
      ModuleRepositoryGateway moduleRepositoryGateway) {
    return new FindModuleByIdUseCaseImpl(moduleRepositoryGateway);
  }

  @Bean
  public FindModulesByCourseIdUseCase findModulesByCourseIdUseCase(
      ModuleRepositoryGateway moduleRepositoryGateway,
      FindCourseByIdUseCase findCourseByIdUseCase) {
    return new FindModulesByCourseIdUseCaseImpl(moduleRepositoryGateway, findCourseByIdUseCase);
  }

  @Bean
  public CreateModuleUseCase createModuleUseCase(
      ModuleRepositoryGateway moduleRepositoryGateway,
      FindCourseByIdUseCase findCourseByIdUseCase,
      AuthenticatedUserGateway authenticatedUserGateway) {
    return new CreateModuleUseCaseImpl(
        moduleRepositoryGateway, findCourseByIdUseCase, authenticatedUserGateway);
  }
}
