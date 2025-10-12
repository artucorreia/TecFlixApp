package br.com.tecflix_app.modules.user.application.usecases;

import br.com.tecflix_app.modules.auth.application.gateways.AuthenticatedUserGateway;
import br.com.tecflix_app.modules.shared.exception.general.ResourceNotFoundException;
import br.com.tecflix_app.modules.user.application.domain.entity.User;

import java.util.logging.Logger;

public class FindMeUseCaseImpl implements FindMeUseCase {
  private final Logger LOGGER = Logger.getLogger(FindMeUseCaseImpl.class.getName());
  private final AuthenticatedUserGateway authenticatedUserGateway;

  public FindMeUseCaseImpl(AuthenticatedUserGateway authenticatedUserGateway) {
    this.authenticatedUserGateway = authenticatedUserGateway;
  }

  @Override
  public User execute() {
    LOGGER.info("Finding authenticated user information");
    return authenticatedUserGateway
        .findUser()
        .orElseThrow(() -> new ResourceNotFoundException("Erro ao resgatar usuário logado"));
  }
}
