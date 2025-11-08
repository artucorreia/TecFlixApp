package br.com.tecflix_app.modules.auth.application.usecases;

import java.util.UUID;

/**
 * Use case responsible for validating a JWT access token and extracting the associated user ID.
 *
 * @author arthurcorreia.dev@gmail.com
 */
public interface ValidateTokenUseCase {

  /**
   * Validates the provided JWT access token and returns the associated user ID.
   *
   * @param token the JWT access token to be validated
   * @return the {@link UUID} of the user associated with the valid token
   */
  UUID execute(String token);
}
