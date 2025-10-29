package br.com.tecflix_app.modules.review.application.usecases;

import br.com.tecflix_app.modules.review.application.domain.entity.Review;

import java.util.List;
import java.util.UUID;

/**
 * Use case responsible for retrieving all reviews associated with a specific course.
 *
 * @author arthurcorreia.dev@gmail.com
 */
public interface FindReviewsByCourseIdUseCase {

  /**
   * Finds and returns all {@link Review} entities associated with the given course ID.
   *
   * @param courseId the unique identifier of the course
   * @return a {@link List} containing all reviews linked to the specified course
   * @throws br.com.tecflix_app.modules.shared.exception.general.ResourceNotFoundException if no
   *     course exist for the provided ID
   */
  List<Review> execute(UUID courseId);
}
