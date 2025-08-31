package br.com.tecflix_app.modules.module.application.usecases;

import br.com.tecflix_app.modules.course.application.domain.entity.Course;
import br.com.tecflix_app.modules.module.application.domain.entity.Module;

/**
 * Use case responsible for retrieving a module by its unique identifier.
 *
 * @author arthurcorreia.dev@gmail.com
 */
public interface FindModuleByIdUseCase {

  /**
   * Finds and returns a {@link Course} by its unique ID.
   *
   * @param id the unique identifier of the course
   * @return the {@link Course} associated with the given ID
   * @throws br.com.tecflix_app.modules.shared.exception.general.ResourceNotFoundException if no
   *     module exists with the provided ID
   */
  Module execute(Long id);
}
