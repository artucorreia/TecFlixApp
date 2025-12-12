package br.com.tecflix_app.modules.emailCode.application.usecases;

import br.com.tecflix_app.modules.emailCode.application.domain.entity.EmailCode;
import br.com.tecflix_app.modules.emailCode.application.gateways.CodeRepositoryGateway;
import br.com.tecflix_app.modules.shared.exception.auth.UserAlreadyIsActive;
import br.com.tecflix_app.modules.shared.exception.general.ResourceNotFoundException;
import br.com.tecflix_app.modules.user.application.domain.entity.User;
import br.com.tecflix_app.modules.user.application.gateways.UserRepositoryGateway;
import br.com.tecflix_app.modules.user.application.usecases.FindUserByIdUseCase;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

public class ValidateAccountCodeUseCaseImpl implements ValidateAccountCodeUseCase {
  private final Logger LOGGER = Logger.getLogger(ValidateAccountCodeUseCaseImpl.class.getName());
  private final CodeRepositoryGateway codeRepositoryGateway;
  private final FindUserByIdUseCase findUserByIdUseCase;
  private final UserRepositoryGateway userRepositoryGateway;

  public ValidateAccountCodeUseCaseImpl(
      CodeRepositoryGateway codeRepositoryGateway,
      FindUserByIdUseCase findUserByIdUseCase,
      UserRepositoryGateway userRepositoryGateway) {
    this.codeRepositoryGateway = codeRepositoryGateway;
    this.findUserByIdUseCase = findUserByIdUseCase;
    this.userRepositoryGateway = userRepositoryGateway;
  }

  @Override
  public void execute(UUID userId, String code) {
    LOGGER.info("Validating email code for user: " + userId);
    Optional<EmailCode> emailCodeOptional = codeRepositoryGateway.findByCodeAndUserId(code, userId);
    if (emailCodeOptional.isEmpty())
      throw new ResourceNotFoundException("Código inválido ou expirado");
    User user = findUserByIdUseCase.execute(userId);
    if (user.getEmailVerified()) throw new UserAlreadyIsActive("Usuário já está ativo");
    user.setEmailVerified(true);
    user.setEmailVerifiedAt(LocalDateTime.now());
    userRepositoryGateway.save(user);
  }
}
