package br.com.tecflix_app.modules.gender.application.usecases;

import br.com.tecflix_app.modules.gender.application.domain.entity.Gender;
import br.com.tecflix_app.modules.gender.application.gateways.GenderRepositoryGateway;
import br.com.tecflix_app.modules.shared.exception.general.ResourceNotFoundException;

import java.util.logging.Logger;

public class FindGenderByIdUseCaseImpl implements FindGenderByIdUseCase {
  private final Logger LOGGER = Logger.getLogger(FindGenderByIdUseCaseImpl.class.getName());
  private final GenderRepositoryGateway genderRepositoryGateway;

  public FindGenderByIdUseCaseImpl(GenderRepositoryGateway genderRepositoryGateway) {
    this.genderRepositoryGateway = genderRepositoryGateway;
  }

  @Override
  public Gender execute(Long id) {
    LOGGER.info("Finding gender by id: " + id);
    return genderRepositoryGateway
        .findById(id)
        .orElseThrow(
            () -> new ResourceNotFoundException("Nenhum gênero encontrado para este id: " + id));
  }
}
