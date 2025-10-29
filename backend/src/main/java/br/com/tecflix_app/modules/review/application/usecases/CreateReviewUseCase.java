package br.com.tecflix_app.modules.review.application.usecases;

import br.com.tecflix_app.modules.review.application.domain.entity.Review;

import java.util.UUID;

/**
 * Use case responsible for creating a new review associated with a specific course.
 *
 * @author arthurcorreia.dev@gmail.com
 */
public interface CreateReviewUseCase {

  /**
   * Creates a new {@link Review} and associates it with the specified course.
   *
   * @param courseId the unique identifier of the course to which the review belongs
   * @param review the review entity to be created
   * @throws br.com.tecflix_app.modules.shared.exception.general.ResourceNotFoundException if no
   *     course exist for the provided ID
   * @throws br.com.tecflix_app.modules.shared.exception.general.ActionNotAllowedException if the
   *     user has already added a review to the course
   */
  void execute(UUID courseId, Review review);
}
