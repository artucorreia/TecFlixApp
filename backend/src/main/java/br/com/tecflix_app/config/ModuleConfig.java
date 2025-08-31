package br.com.tecflix_app.config;

import br.com.tecflix_app.modules.course.application.usecases.FindCourseByIdUseCase;
import br.com.tecflix_app.modules.module.application.gateways.ModuleRepositoryGateway;
import br.com.tecflix_app.modules.module.application.usecases.CreateModuleUseCase;
import br.com.tecflix_app.modules.module.application.usecases.CreateModuleUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModuleConfig {

  @Bean
  public CreateModuleUseCase createModuleUseCase(
      ModuleRepositoryGateway moduleRepositoryGateway,
      FindCourseByIdUseCase findCourseByIdUseCase) {
    return new CreateModuleUseCaseImpl(moduleRepositoryGateway, findCourseByIdUseCase);
  }
}
