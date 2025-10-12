package br.com.tecflix_app.modules.professorData.application.usecases;

import br.com.tecflix_app.modules.professorData.application.domain.entity.ProfessorData;
import br.com.tecflix_app.modules.professorData.application.gateways.ProfessorDataRepositoryGateway;
import br.com.tecflix_app.modules.shared.exception.general.ResourceNotFoundException;
import br.com.tecflix_app.modules.social.application.usecases.FindSocialsAuthenticatedUserByUserIdUseCase;

import java.util.UUID;
import java.util.logging.Logger;

public class FindAuthenticatedUserProfessorDataByUserIdUseCaseImpl
    implements FindAuthenticatedUserProfessorDataByUserIdUseCase {
  private final Logger LOGGER =
      Logger.getLogger(FindAuthenticatedUserProfessorDataByUserIdUseCaseImpl.class.getName());
  private final ProfessorDataRepositoryGateway professorDataRepositoryGateway;

  public FindAuthenticatedUserProfessorDataByUserIdUseCaseImpl(
      ProfessorDataRepositoryGateway professorDataRepositoryGateway) {
    this.professorDataRepositoryGateway = professorDataRepositoryGateway;
  }

  @Override
  public ProfessorData execute(UUID userId) {
    LOGGER.info("Find professor data user's settings by user id: " + userId);
    return professorDataRepositoryGateway.findAuthenticatedByUserId(userId).orElse(null);
  }
}
