package br.com.tecflix_app.modules.professorData.application.usecases;

import br.com.tecflix_app.modules.professorData.application.domain.entity.ProfessorData;
import br.com.tecflix_app.modules.professorData.application.gateways.ProfessorDataRepositoryGateway;

import java.util.Optional;
import java.util.logging.Logger;

public class FindProfessorDataByPhoneNumberUseCaseImpl
    implements FindProfessorDataByPhoneNumberUseCase {
  private final Logger LOGGER =
      Logger.getLogger(FindProfessorDataByPhoneNumberUseCaseImpl.class.getName());
  private final ProfessorDataRepositoryGateway professorDataRepositoryGateway;

  public FindProfessorDataByPhoneNumberUseCaseImpl(
      ProfessorDataRepositoryGateway professorDataRepositoryGateway) {
    this.professorDataRepositoryGateway = professorDataRepositoryGateway;
  }

  @Override
  public Optional<ProfessorData> execute(String phoneNumber) {
    LOGGER.info("Finding professor data by phone number: " + phoneNumber);
    return professorDataRepositoryGateway.findByPhoneNumber(phoneNumber);
  }
}
