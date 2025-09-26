package br.com.tecflix_app.modules.professorData.application.usecases;

import br.com.tecflix_app.modules.professorData.application.domain.entity.ProfessorData;
import br.com.tecflix_app.modules.professorData.application.gateways.ProfessorDataRepositoryGateway;
import br.com.tecflix_app.modules.shared.exception.general.ResourceNotFoundException;

import java.util.UUID;
import java.util.logging.Logger;

public class FindProfessorDataProfileByUserIdUseCaseImpl
    implements FindProfessorDataProfileByUserIdUseCase {
  private final Logger LOGGER =
      Logger.getLogger(FindProfessorDataProfileByUserIdUseCaseImpl.class.getName());
  private final ProfessorDataRepositoryGateway professorDataRepositoryGateway;

  public FindProfessorDataProfileByUserIdUseCaseImpl(
      ProfessorDataRepositoryGateway professorDataRepositoryGateway) {
    this.professorDataRepositoryGateway = professorDataRepositoryGateway;
  }

  @Override
  public ProfessorData execute(UUID userId) {
    LOGGER.info("Find professor data profile by user id: " + userId);
    return professorDataRepositoryGateway
        .findProfileByUserId(userId)
        .orElseThrow(
            () ->
                new ResourceNotFoundException(
                    "Nenhum dado de professor encontrado para o usuario de id: " + userId));
  }
}
