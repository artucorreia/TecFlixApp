package br.com.tecflix_app.modules.user.application.usecases;

import br.com.tecflix_app.modules.shared.exception.general.ResourceNotFoundException;
import br.com.tecflix_app.modules.user.application.domain.entity.User;
import br.com.tecflix_app.modules.user.application.gateways.UserRepositoryGateway;

import java.util.logging.Logger;

public class FindUserByEmailUseCaseImpl implements FindUserByEmailUseCase {
  private final Logger LOGGER = Logger.getLogger(FindUserByEmailUseCaseImpl.class.getName());
  private final UserRepositoryGateway userRepositoryGateway;

  public FindUserByEmailUseCaseImpl(UserRepositoryGateway userRepositoryGateway) {
    this.userRepositoryGateway = userRepositoryGateway;
  }

  @Override
  public User execute(String email) {
    LOGGER.info("Finding user by email: " + email);
    return userRepositoryGateway
        .findByEmail(email)
        .orElseThrow(() -> new ResourceNotFoundException("Nenhum usuário encontrado para este email"));
  }
}
