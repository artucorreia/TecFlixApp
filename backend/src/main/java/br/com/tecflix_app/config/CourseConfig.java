package br.com.tecflix_app.config;

import br.com.tecflix_app.modules.auth.application.gateways.AuthenticatedUserGateway;
import br.com.tecflix_app.modules.course.application.gateways.CourseRepositoryGateway;
import br.com.tecflix_app.modules.course.application.usecases.CreateCourseUseCase;
import br.com.tecflix_app.modules.course.application.usecases.CreateCourseUseCaseImpl;
import br.com.tecflix_app.modules.course.application.usecases.FindCourseByIdUseCase;
import br.com.tecflix_app.modules.course.application.usecases.FindCourseByIdUseCaseImpl;
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
  public CreateCourseUseCase createCourseUseCase(
      CourseRepositoryGateway courseRepositoryGateway,
      AuthenticatedUserGateway authenticatedUserGateway,
      FindTagByIdCase findTagByIdCase,
      FindAllTagsByIdUseCase findAllTagsByIdUseCase,
      FindUserByIdUseCase findUserByIdUseCase) {
    return new CreateCourseUseCaseImpl(
        courseRepositoryGateway,
        authenticatedUserGateway,
        findTagByIdCase,
        findAllTagsByIdUseCase,
        findUserByIdUseCase);
  }
}
