package br.com.tecflix_app.modules.emailCode.application.usecases;

import br.com.tecflix_app.modules.emailCode.application.domain.entity.EmailCode;

/**
 * Use case responsible for generating, persisting and sand a password reset code for a user.
 *
 * @author arthurcorreia.dev@gmail.com
 */
public interface CreatePasswordResetCodeUseCase {

  /**
   * Generates and returns a new password reset {@link EmailCode} for the user with the given email.
   *
   * @param email the email address of the user requesting a password reset
   * @return the generated {@link EmailCode} entity
   * @throws br.com.tecflix_app.modules.shared.exception.general.ResourceNotFoundException if no
   *     user exists with the provided email
   */
  EmailCode execute(String email);
}
