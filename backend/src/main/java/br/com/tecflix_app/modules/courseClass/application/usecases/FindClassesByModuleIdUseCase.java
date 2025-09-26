package br.com.tecflix_app.modules.courseClass.application.usecases;

import br.com.tecflix_app.modules.courseClass.application.domain.entity.Class;

import java.util.Set;

/**
 * Use case responsible for retrieving all classes that belong to a specific course module.
 *
 * <p>This use case provides access to the set of {@link Class} entities associated with a given
 * module, identified by its unique ID.
 *
 * @author arthurcorreia.dev@gmail.com
 */
public interface FindClassesByModuleIdUseCase {

  /**
   * Finds and returns all {@link Class} entities associated with the given module ID.
   *
   * @param moduleId the unique identifier of the course module
   * @return a {@link Set} containing all classes associated with the specified module
   * @throws br.com.tecflix_app.modules.shared.exception.general.ResourceNotFoundException if no
   *     module exists with the provided ID
   */
  Set<Class> execute(Long moduleId);
}
