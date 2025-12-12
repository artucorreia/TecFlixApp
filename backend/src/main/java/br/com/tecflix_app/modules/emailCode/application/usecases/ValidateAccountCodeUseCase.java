package br.com.tecflix_app.modules.emailCode.application.usecases;

import java.util.UUID;

/**
 * Use case responsible for validating an account verification code sent to a user.
 *
 * @author arthurcorreia.dev@gmail.com
 */
public interface ValidateAccountCodeUseCase {

  /**
   * Validates the verification code associated with the given user.
   *
   * @param userId the unique identifier of the user whose code is being validated
   * @param code the verification code provided for validation
   * @throws br.com.tecflix_app.modules.shared.exception.general.ResourceNotFoundException if no
   *     user or no code exists with the provided ID/code
   * @throws br.com.tecflix_app.modules.shared.exception.auth.UserAlreadyIsActive if the user
   *     already active
   */
  void execute(UUID userId, String code);
}
