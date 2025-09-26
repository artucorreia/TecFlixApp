package br.com.tecflix_app.modules.occupation.application.usecases;

import br.com.tecflix_app.modules.occupation.application.domain.entity.Occupation;
import br.com.tecflix_app.modules.occupation.application.gateways.OccupationRepositoryGateway;
import br.com.tecflix_app.modules.shared.exception.general.ResourceNotFoundException;

import java.util.logging.Logger;

public class FindOccupationByIdUseCaseImpl implements FindOccupationByIdUseCase {
  private final Logger LOGGER = Logger.getLogger(FindOccupationByIdUseCaseImpl.class.getName());
  private final OccupationRepositoryGateway occupationRepositoryGateway;

  public FindOccupationByIdUseCaseImpl(OccupationRepositoryGateway occupationRepositoryGateway) {
    this.occupationRepositoryGateway = occupationRepositoryGateway;
  }

  @Override
  public Occupation execute(Long id) {
    LOGGER.info("Finding occupation by id: " + id);
    return occupationRepositoryGateway
        .findById(id)
        .orElseThrow(
            () ->
                new ResourceNotFoundException("Nenhuma profissão encontrada para este id: " + id));
  }
}
