package br.com.tecflix_app.modules.user.application.usecases;

import br.com.tecflix_app.modules.shared.exception.general.ResourceNotFoundException;
import br.com.tecflix_app.modules.user.application.domain.entity.User;
import br.com.tecflix_app.modules.user.application.gateways.UserRepositoryGateway;

import java.util.UUID;
import java.util.logging.Logger;

public class FindUserByIdUseCaseImpl implements FindUserByIdUseCase {
  private final Logger LOGGER = Logger.getLogger(FindUserByIdUseCaseImpl.class.getName());
  private final UserRepositoryGateway userRepositoryGateway;

  public FindUserByIdUseCaseImpl(UserRepositoryGateway userRepositoryGateway) {
    this.userRepositoryGateway = userRepositoryGateway;
  }

  @Override
  public User execute(UUID id) {
    LOGGER.info("Finding user by id");
    return userRepositoryGateway
        .findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Nenhum usuário encontrado para este id"));
  }
}
