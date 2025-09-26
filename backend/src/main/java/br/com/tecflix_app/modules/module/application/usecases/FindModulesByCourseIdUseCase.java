package br.com.tecflix_app.modules.module.application.usecases;

import br.com.tecflix_app.modules.module.application.domain.entity.Module;

import java.util.Set;
import java.util.UUID;

/**
 * Use case responsible for retrieving all modules that belong to a specific course.
 *
 * <p>This use case provides access to the set of {@link Module} entities associated with a given
 * course, identified by its unique ID.
 *
 * @author arthurcorreia.dev@gmail.com
 */
public interface FindModulesByCourseIdUseCase {

  /**
   * Finds and returns all {@link Module} entities associated with the given course ID.
   *
   * @param courseId the unique identifier of the course
   * @return a {@link Set} containing all modules associated with the specified course
   * @throws br.com.tecflix_app.modules.shared.exception.general.ResourceNotFoundException if no
   *     course exists with the provided ID
   */
  Set<Module> execute(UUID courseId);
}
