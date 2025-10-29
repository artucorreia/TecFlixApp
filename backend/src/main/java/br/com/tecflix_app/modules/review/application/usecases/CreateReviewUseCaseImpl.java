package br.com.tecflix_app.modules.review.application.usecases;

import br.com.tecflix_app.modules.auth.application.gateways.AuthenticatedUserGateway;
import br.com.tecflix_app.modules.course.application.domain.entity.Course;
import br.com.tecflix_app.modules.course.application.usecases.FindCourseByIdUseCase;
import br.com.tecflix_app.modules.review.application.domain.entity.Review;
import br.com.tecflix_app.modules.review.application.gateways.ReviewRepositoryGateway;
import br.com.tecflix_app.modules.shared.exception.general.ActionNotAllowedException;
import br.com.tecflix_app.modules.shared.exception.general.ResourceNotFoundException;
import br.com.tecflix_app.modules.user.application.domain.entity.User;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

public class CreateReviewUseCaseImpl implements CreateReviewUseCase {
  private final Logger LOGGER = Logger.getLogger(CreateReviewUseCaseImpl.class.getName());
  private final ReviewRepositoryGateway reviewRepositoryGateway;
  private final FindCourseByIdUseCase findCourseByIdUseCase;
  private final AuthenticatedUserGateway authenticatedUserGateway;

  public CreateReviewUseCaseImpl(
      ReviewRepositoryGateway reviewRepositoryGateway,
      FindCourseByIdUseCase findCourseByIdUseCase,
      AuthenticatedUserGateway authenticatedUserGateway) {
    this.reviewRepositoryGateway = reviewRepositoryGateway;
    this.findCourseByIdUseCase = findCourseByIdUseCase;
    this.authenticatedUserGateway = authenticatedUserGateway;
  }

  @Override
  public void execute(UUID courseId, Review review) {
    LOGGER.info("Creating a new review for course id: " + courseId);

    Course course = findCourseByIdUseCase.execute(courseId);
    if (course.getDeleted())
      throw new ActionNotAllowedException(
          "Não é possível adicionar avaliações para cursos não ativos");
    if (!course.getApproved())
      throw new ActionNotAllowedException(
          "Não é possível adicionar avaliações para cursos não aprovados");

    User user =
        authenticatedUserGateway
            .findUser()
            .orElseThrow(() -> new ResourceNotFoundException("Erro ao resgatar usuário logado"));

    Optional<Review> reviewOptional =
        reviewRepositoryGateway.findByCourseIdAndUserId(course.getId(), user.getId());
    if (reviewOptional.isPresent())
      throw new ActionNotAllowedException("Você já avaliou este curso");

    review.setComment(review.getComment().trim());
    review.setDeleted(false);
    review.setCreatedAt(LocalDateTime.now());
    review.setCourse(course);
    review.setUser(user);
    reviewRepositoryGateway.save(review);
  }
}
