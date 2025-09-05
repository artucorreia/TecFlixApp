package br.com.tecflix_app.modules.courseClass.application.usecases;

import br.com.tecflix_app.modules.courseClass.application.domain.entity.Class;

/**
 * Use case responsible for creating a new module's class in the system.
 *
 * @author arthurcorreia.dev@gmail.com
 */
public interface CreateClassUseCase {

  /**
   * Creates and persists a new {@link Class}.
   *
   * @param courseClass the class entity containing initial information (title, videoPath, etc.)
   * @throws br.com.tecflix_app.modules.shared.exception.general.ResourceNotFoundException if the
   *     module does not exist
   * @throws br.com.tecflix_app.modules.shared.exception.auth.AuthenticatedUserException if the
   *     authenticated user is not the module owner
   */
  void execute(Class courseClass);
}
