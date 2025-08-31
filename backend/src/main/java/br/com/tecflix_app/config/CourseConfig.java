package br.com.tecflix_app.config;

import br.com.tecflix_app.modules.auth.application.gateways.AuthenticatedUserGateway;
import br.com.tecflix_app.modules.course.application.gateways.CourseRepositoryGateway;
import br.com.tecflix_app.modules.course.application.usecases.*;
import br.com.tecflix_app.modules.tag.application.usecases.FindAllTagsByIdUseCase;
import br.com.tecflix_app.modules.tag.application.usecases.FindTagByIdCase;
import br.com.tecflix_app.modules.user.application.usecases.FindUserByIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CourseConfig {

  @Bean
  public FindCourseByIdUseCase findCourseByIdUseCase(
      CourseRepositoryGateway courseRepositoryGateway) {
    return new FindCourseByIdUseCaseImpl(courseRepositoryGateway);
  }

  @Bean
  public FindCourseDetailsByIdUseCase findCourseDetailsByIdUseCase(
      CourseRepositoryGateway courseRepositoryGateway) {
    return new FindCourseDetailsByIdUseCaseImpl(courseRepositoryGateway);
  }

  @Bean
  public CreateCourseUseCase createCourseUseCase(
      CourseRepositoryGateway courseRepositoryGateway,
      AuthenticatedUserGateway authenticatedUserGateway,
      FindAllTagsByIdUseCase findAllTagsByIdUseCase,
      FindUserByIdUseCase findUserByIdUseCase) {
    return new CreateCourseUseCaseImpl(
        courseRepositoryGateway,
        authenticatedUserGateway,
        findAllTagsByIdUseCase,
        findUserByIdUseCase);
  }
}
