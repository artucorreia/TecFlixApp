package br.com.tecflix_app.modules.user.application.usecases;

import br.com.tecflix_app.modules.user.application.domain.entity.User;

/**
 * Use case responsible for retrieving a user by their email address.
 *
 * @author arthurcorreia.dev@gmail.com
 */
public interface FindUserByEmailUseCase {

  /**
   * Finds and returns the {@link User} entity associated with the provided email address.
   *
   * @param email the unique email address of the user
   * @return the {@link User} associated with the given email
   * @throws br.com.tecflix_app.modules.shared.exception.general.ResourceNotFoundException if no
   *     user exists with the provided email
   */
  User execute(String email);
}
