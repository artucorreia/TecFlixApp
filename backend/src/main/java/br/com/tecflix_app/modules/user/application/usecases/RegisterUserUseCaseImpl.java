package br.com.tecflix_app.modules.user.application.usecases;

import br.com.tecflix_app.modules.emailCode.application.domain.entity.EmailCode;
import br.com.tecflix_app.modules.emailCode.application.gateways.CodeSenderGateway;
import br.com.tecflix_app.modules.emailCode.application.usecases.CreateAccountValidationCodeUseCase;
import br.com.tecflix_app.modules.shared.exception.general.RepeatedDataException;
import br.com.tecflix_app.modules.user.application.domain.entity.User;
import br.com.tecflix_app.modules.user.application.domain.enums.Role;
import br.com.tecflix_app.modules.user.application.gateways.UserRepositoryGateway;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.logging.Logger;

public class RegisterUserUseCaseImpl implements RegisterUserUseCase {
  private final Logger LOGGER = Logger.getLogger(RegisterUserUseCaseImpl.class.getName());

  private final UserRepositoryGateway userRepositoryGateway;
  private final CreateAccountValidationCodeUseCase createAccountValidationCodeUseCase;
  private final CodeSenderGateway codeSenderGateway;

  public RegisterUserUseCaseImpl(
      UserRepositoryGateway userRepositoryGateway,
      CreateAccountValidationCodeUseCase createAccountValidationCodeUseCase,
      CodeSenderGateway codeSenderGateway) {
    this.userRepositoryGateway = userRepositoryGateway;
    this.createAccountValidationCodeUseCase = createAccountValidationCodeUseCase;
    this.codeSenderGateway = codeSenderGateway;
  }

  @Override
  public void execute(User user) {
    LOGGER.info("Registering a new user");

    boolean emailAlreadyInUse = checkIfEmailAlreadyExists(user.getEmail().trim());
    if (emailAlreadyInUse) throw new RepeatedDataException("Este email já está em uso");

    User savedUser = userRepositoryGateway.save(treatFields(user));
    EmailCode savedEmailCode = createAccountValidationCodeUseCase.execute(savedUser.getId());
    codeSenderGateway.sendCodeToValidateUser(
        savedUser.getId(), user.getEmail(), user.getName(), savedEmailCode.getCode());
  }

  private boolean checkIfEmailAlreadyExists(String email) {
    LOGGER.info("Checking if the email is already in use");
    return userRepositoryGateway.findByEmail(email).isPresent();
  }

  private User treatFields(User user) {
    LOGGER.info("Treating user fields");
    String passwordEncoded = new BCryptPasswordEncoder().encode(user.getPassword().trim());
    user.setName(user.getName().trim());
    user.setEmail(user.getEmail().trim());
    user.setPassword(passwordEncoded);
    user.setRole(Role.USER);
    user.setActive(false);
    user.setCreatedAt(LocalDateTime.now());
    return user;
  }
}
