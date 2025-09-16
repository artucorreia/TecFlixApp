package br.com.tecflix_app.modules.emailCode.infra.gateways;

import br.com.tecflix_app.modules.emailCode.application.gateways.CodeSenderGateway;
import br.com.tecflix_app.modules.shared.email.EmailSenderService;
import br.com.tecflix_app.modules.shared.exception.email.EmailSendingException;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;
import java.util.logging.Logger;

@Component
@RequiredArgsConstructor
public class CodeEmailSenderGateway implements CodeSenderGateway {
  private final Logger LOGGER = Logger.getLogger(CodeEmailSenderGateway.class.getName());
  private final EmailSenderService emailSenderService;

  @Value("${app.frontend.url}")
  private String frontendUrl;

  private static final String FROM_ADDRESS = "no-reply@tecflix";
  private static final String EMAIL_TEMPLATE_PATH = "templates/generic-mail-template.html";

  private static final String VALIDATION_PATH_TEMPLATE =
      "/sign-up/authenticate-code?code=%s&userId=%s";
  private static final String RESET_PASSWORD_PATH_TEMPLATE =
      "/sign-in/reset-password?code=%s&userId=%s";

  private static final String VALIDATION_SUBJECT = "Validar E-mail";
  private static final String VALIDATION_MESSAGE =
      "Clique no botão abaixo para validar o seu e-mail:";
  private static final String VALIDATION_BUTTON_TEXT = "Validar E-mail";
  private static final String VALIDATION_FOOTER = "Acesse o link para validar seu e-mail";

  private static final String RESET_SUBJECT = "Resetar senha";
  private static final String RESET_MESSAGE = "Clique no botão abaixo para resetar sua senha:";
  private static final String RESET_BUTTON_TEXT = "Resetar Senha";
  private static final String RESET_FOOTER = "Acesse o link para resetar sua senha";

  @Override
  public void sendCodeToValidateUser(UUID userId, String email, String name, String code) {
    LOGGER.info(String.format("Sending validation code to user: %s (%s)", name, email));

    String path = buildValidationPath(code, userId);
    String url = frontendUrl + path;
    try {
      emailSenderService.sendEmailCode(
          VALIDATION_SUBJECT,
          FROM_ADDRESS,
          EMAIL_TEMPLATE_PATH,
          email,
          name,
          VALIDATION_MESSAGE,
          VALIDATION_BUTTON_TEXT,
          url,
          VALIDATION_FOOTER);
    } catch (MessagingException ex) {
      throw new EmailSendingException("Ocorreu um erro ao definir as propriedades do email");
    } catch (IOException ex) {
      throw new EmailSendingException("Ocorreu um erro ao resgatar o template de email");
    } catch (Exception ex) {
      throw new EmailSendingException("Ocorreu um erro ao enviar o email");
    }
  }

  @Override
  public void sendCodeToResetPassword(UUID userId, String email, String name, String code) {
    LOGGER.info(String.format("Sending password reset code to user: %s (%s)", name, email));

    String path = buildResetPasswordPath(code, userId);
    String url = frontendUrl + path;
    try {
      emailSenderService.sendEmailCode(
          RESET_SUBJECT,
          FROM_ADDRESS,
          EMAIL_TEMPLATE_PATH,
          email,
          name,
          RESET_MESSAGE,
          RESET_BUTTON_TEXT,
          url,
          RESET_FOOTER);
    } catch (MessagingException ex) {
      throw new EmailSendingException("Ocorreu um erro ao definir as propriedades do email");
    } catch (IOException ex) {
      throw new EmailSendingException("Ocorreu um erro ao resgatar o template de email");
    } catch (Exception ex) {
      throw new EmailSendingException("Ocorreu um erro ao enviar o email");
    }
  }

  private String buildValidationPath(String code, UUID userId) {
    String relativePath = String.format(VALIDATION_PATH_TEMPLATE, code, userId);
    return frontendUrl + relativePath;
  }

  private String buildResetPasswordPath(String code, UUID userId) {
    String relativePath = String.format(RESET_PASSWORD_PATH_TEMPLATE, code, userId);
    return frontendUrl + relativePath;
  }
}
