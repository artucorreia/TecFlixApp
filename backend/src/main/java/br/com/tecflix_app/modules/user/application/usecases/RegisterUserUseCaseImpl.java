package br.com.tecflix_app.modules.user.application.usecases;

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

  public RegisterUserUseCaseImpl(UserRepositoryGateway userRepositoryGateway) {
    this.userRepositoryGateway = userRepositoryGateway;
  }

  @Override
  public void execute(User user) {
    LOGGER.info("Registering a new user");
    checkEmail(user.getEmail().trim());
    userRepositoryGateway.save(treatFields(user));
  }

  private void checkEmail(String email) {
    LOGGER.info("Checking if email is already in use");
    if (userRepositoryGateway.findByEmail(email).isPresent())
      throw new RepeatedDataException("Este email já está em uso");
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
