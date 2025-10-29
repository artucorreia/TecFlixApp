package br.com.tecflix_app.modules.review.application.usecases;

import br.com.tecflix_app.modules.course.application.domain.entity.Course;
import br.com.tecflix_app.modules.course.application.usecases.FindCourseByIdUseCase;
import br.com.tecflix_app.modules.review.application.domain.entity.Review;
import br.com.tecflix_app.modules.review.application.gateways.ReviewRepositoryGateway;
import br.com.tecflix_app.modules.shared.exception.general.ActionNotAllowedException;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

public class FindReviewsByCourseIdUseCaseImpl implements FindReviewsByCourseIdUseCase {
  private final Logger LOGGER = Logger.getLogger(FindReviewsByCourseIdUseCaseImpl.class.getName());
  private final ReviewRepositoryGateway reviewRepositoryGateway;
  private final FindCourseByIdUseCase findCourseByIdUseCase;

  public FindReviewsByCourseIdUseCaseImpl(ReviewRepositoryGateway reviewRepositoryGateway, FindCourseByIdUseCase findCourseByIdUseCase) {
    this.reviewRepositoryGateway = reviewRepositoryGateway;
    this.findCourseByIdUseCase = findCourseByIdUseCase;
  }

  @Override
  public List<Review> execute(UUID courseId) {
    LOGGER.info("Finding reviews by course id: " + courseId);
    Course course =findCourseByIdUseCase.execute(courseId);
    if (course.getDeleted()) throw new ActionNotAllowedException("Não é possível resgatar avaliações de cursos não ativos");
    if (!course.getApproved()) throw new ActionNotAllowedException("Não é possível resgatar avaliações de cursos não aprovados");
    return reviewRepositoryGateway.findByCourseId(courseId);
  }
}
