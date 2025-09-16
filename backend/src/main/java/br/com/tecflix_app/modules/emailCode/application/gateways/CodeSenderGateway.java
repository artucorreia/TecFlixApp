package br.com.tecflix_app.modules.emailCode.application.gateways;

import java.util.UUID;

/**
 * Gateway interface for sending various types of codes to users.
 *
 * <p>This interface defines the contract for sending codes for user validation and password reset,
 * abstracting the underlying communication mechanism (e.g., email, SMS).
 *
 * @author arthurcorreia.dev@gmail.com
 */
public interface CodeSenderGateway {

  /**
   * Sends a validation code to a new user's email address.
   *
   * <p>This method is used during the user registration process to verify the user's email address.
   *
   * @param userId The unique identifier of the user.
   * @param email The email address of the user.
   * @param name The name of the user.
   * @param code The unique code to be sent for validation.
   * @throws br.com.tecflix_app.modules.shared.exception.email.EmailSendingException if an error
   *     occurs during the email sending process, * such as a misconfigured email property, an issue
   *     with the email template, * or a general failure in dispatching the email.
   */
  void sendCodeToValidateUser(UUID userId, String email, String name, String code);

  /**
   * Sends a password reset code to a user's email address.
   *
   * <p>This method is used when a user requests to reset their password.
   *
   * @param userId The unique identifier of the user.
   * @param email The email address of the user.
   * @param name The name of the user.
   * @param code The unique code to be sent for password reset.
   * @throws br.com.tecflix_app.modules.shared.exception.email.EmailSendingException if an error
   *     occurs during the email sending process, * such as a misconfigured email property, an issue
   *     with the email template, * or a general failure in dispatching the email.
   */
  void sendCodeToResetPassword(UUID userId, String email, String name, String code);
}
