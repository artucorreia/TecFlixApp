package br.com.tecflix_app.modules.professorData.application.usecases;

import br.com.tecflix_app.modules.professorData.application.domain.entity.ProfessorData;
import br.com.tecflix_app.modules.professorData.application.gateways.ProfessorDataRepositoryGateway;

import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

public class FindProfessorDataByUserIdUseCaseImpl implements FindProfessorDataByUserIdUseCase {
  private final Logger LOGGER =
      Logger.getLogger(FindProfessorDataByUserIdUseCaseImpl.class.getName());
  private final ProfessorDataRepositoryGateway professorDataRepositoryGateway;

  public FindProfessorDataByUserIdUseCaseImpl(
      ProfessorDataRepositoryGateway professorDataRepositoryGateway) {
    this.professorDataRepositoryGateway = professorDataRepositoryGateway;
  }

  @Override
  public Optional<ProfessorData> execute(UUID userId) {
    LOGGER.info("Finding professor data by user id: " + userId.toString());
    return professorDataRepositoryGateway.findByUserId(userId);
  }
}
