package br.com.tecflix_app.modules.module.application.usecases;

import br.com.tecflix_app.modules.course.application.domain.entity.Course;
import br.com.tecflix_app.modules.module.application.domain.entity.Module;

/**
 * Use case responsible for creating a new course's module in the system.
 *
 * @author arthurcorreia.dev@gmail.com
 */
public interface CreateModuleUseCase {

  /**
   * Creates and persists a new {@link Course}.
   *
   * @param module the module entity containing initial information
   * @throws br.com.tecflix_app.modules.shared.exception.general.ResourceNotFoundException if the
   *     course does not exist
   * @throws br.com.tecflix_app.modules.shared.exception.auth.AuthenticatedUserException if the
   *     authenticated user is not the course owner
   */
  void execute(Module module);
}
