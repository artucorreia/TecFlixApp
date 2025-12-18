package br.com.tecflix_app.modules.emailCode.application.usecases;

import br.com.tecflix_app.modules.emailCode.application.domain.entity.EmailCode;
import br.com.tecflix_app.modules.emailCode.application.gateways.CodeSenderGateway;
import br.com.tecflix_app.modules.user.application.domain.entity.User;
import br.com.tecflix_app.modules.user.application.usecases.FindUserByEmailUseCase;

import java.util.logging.Logger;

public class CreatePasswordResetCodeUseCaseImpl implements CreatePasswordResetCodeUseCase {
  private final Logger LOGGER =
      Logger.getLogger(CreatePasswordResetCodeUseCaseImpl.class.getName());
  private final FindUserByEmailUseCase findUserByEmailUseCase;
  private final CreateCodeUseCase createCodeUseCase;
  private final CodeSenderGateway codeSenderGateway;

  public CreatePasswordResetCodeUseCaseImpl(
      FindUserByEmailUseCase findUserByEmailUseCase,
      CreateCodeUseCase createCodeUseCase,
      CodeSenderGateway codeSenderGateway) {
    this.findUserByEmailUseCase = findUserByEmailUseCase;
    this.createCodeUseCase = createCodeUseCase;
    this.codeSenderGateway = codeSenderGateway;
  }

  @Override
  public EmailCode execute(String email) {
    LOGGER.info("Creating a code to reset user's password");
    User user = findUserByEmailUseCase.execute(email);
    EmailCode emailCode = createCodeUseCase.execute(user.getId());
    codeSenderGateway.sendCodeToResetPassword(user.getId(), user.getEmail(), user.getName(), emailCode.getCode());
    return emailCode;
  }
}
