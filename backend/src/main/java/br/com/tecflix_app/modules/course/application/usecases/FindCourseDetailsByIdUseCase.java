package br.com.tecflix_app.modules.course.application.usecases;

import br.com.tecflix_app.modules.course.application.domain.entity.Course;

import java.util.UUID;

/**
 * Use case responsible for retrieving a course by its unique identifier.
 *
 * @author arthurcorreia.dev@gmail.com
 */
public interface FindCourseDetailsByIdUseCase {

  /**
   * Finds and returns a {@link Course} by its unique ID.
   *
   * @param id the unique identifier of the course
   * @return the {@link Course} associated with the given ID
   * @throws br.com.tecflix_app.modules.shared.exception.general.ResourceNotFoundException if no
   *     course exists with the provided ID
   */
  Course execute(UUID id);
}
