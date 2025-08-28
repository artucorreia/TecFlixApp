package br.com.tecflix_app.modules.user.application.usecases;

import br.com.tecflix_app.modules.user.application.domain.entity.User;

import java.util.UUID;

/**
 * Use case responsible for retrieving a user by its unique identifier.
 *
 * @author arthurcorreia.dev@gmail.com
 */
public interface FindUserByIdUseCase {

  /**
   * Finds and returns a {@link User} by its unique ID.
   *
   * @param id the unique identifier of the user
   * @return the {@link User} associated with the given ID
   * @throws br.com.tecflix_app.modules.shared.exception.general.ResourceNotFoundException if no
   *     user exists with the provided ID
   */
  User execute(UUID id);
}
