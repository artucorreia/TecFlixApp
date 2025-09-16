package br.com.tecflix_app.modules.user.application.usecases;

import br.com.tecflix_app.modules.user.application.domain.entity.User;

/**
 * Use case responsible for registering a new user in the system.
 *
 * @author arthurcorreia.dev@gmail.com
 */
public interface RegisterUserUseCase {

  /**
   * Registers a new {@link User} in the system.
   *
   * @param user The user object containing the data to be registered.
   * @throws br.com.tecflix_app.modules.shared.exception.general.RepeatedDataException if a user
   *     with the same email already exists.
   */
  void execute(User user);
}
