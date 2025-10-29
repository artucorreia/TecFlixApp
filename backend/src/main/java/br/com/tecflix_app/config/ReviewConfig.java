package br.com.tecflix_app.config;

import br.com.tecflix_app.modules.auth.application.gateways.AuthenticatedUserGateway;
import br.com.tecflix_app.modules.course.application.usecases.FindCourseByIdUseCase;
import br.com.tecflix_app.modules.review.application.gateways.ReviewRepositoryGateway;
import br.com.tecflix_app.modules.review.application.usecases.CreateReviewUseCase;
import br.com.tecflix_app.modules.review.application.usecases.CreateReviewUseCaseImpl;
import br.com.tecflix_app.modules.review.application.usecases.FindReviewsByCourseIdUseCase;
import br.com.tecflix_app.modules.review.application.usecases.FindReviewsByCourseIdUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReviewConfig {

  @Bean
  public FindReviewsByCourseIdUseCase findReviewsByCourseIdUseCase(
      ReviewRepositoryGateway reviewRepositoryGateway,
      FindCourseByIdUseCase findCourseByIdUseCase) {
    return new FindReviewsByCourseIdUseCaseImpl(reviewRepositoryGateway, findCourseByIdUseCase);
  }

  @Bean
  public CreateReviewUseCase createReviewUseCase(
      ReviewRepositoryGateway reviewRepositoryGateway,
      FindCourseByIdUseCase findCourseByIdUseCase,
      AuthenticatedUserGateway authenticatedUserGateway) {
    return new CreateReviewUseCaseImpl(
        reviewRepositoryGateway, findCourseByIdUseCase, authenticatedUserGateway);
  }
}
