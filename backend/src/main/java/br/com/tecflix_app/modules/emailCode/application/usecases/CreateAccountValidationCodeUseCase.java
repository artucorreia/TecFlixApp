package br.com.tecflix_app.modules.emailCode.application.usecases;

import br.com.tecflix_app.modules.emailCode.application.domain.entity.EmailCode;

import java.util.UUID;

/**
 * Use case responsible for creating and persisting a validation code for a user's new account.
 *
 * <p>This use case encapsulates the business logic for generating a unique code and associating it
 * with a specific user, as a step in the account registration process. The generated code
 * is intended to be sent to the user (e.g., via email) for verification.
 */
public interface CreateAccountValidationCodeUseCase {

  /**
   * Executes the use case to create a new validation code for a given user ID.
   *
   * @param userId The unique identifier of the user for whom the validation code is being created.
   * @return The newly created and persisted {@link EmailCode} entity.
   */
  EmailCode execute(UUID userId);
}
