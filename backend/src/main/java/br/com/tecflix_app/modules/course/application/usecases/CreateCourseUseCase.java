package br.com.tecflix_app.modules.course.application.usecases;

import br.com.tecflix_app.modules.course.application.domain.entity.Course;

/**
 * Use case responsible for creating a new course in the application.
 *
 * @author arthurcorreia.dev@gmail.com
 */
public interface CreateCourseUseCase {

  /**
   * Creates and persists a new {@link Course}.
   *
   * @param course the course entity containing initial information (title, description, tags, etc.)
   * @throws br.com.tecflix_app.modules.shared.exception.general.ResourceNotFoundException if the
   *     professor or any tag does not exist
   */
  void execute(Course course);
}
