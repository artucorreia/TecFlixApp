package br.com.tecflix_app.modules.module.application.usecases;

import br.com.tecflix_app.modules.module.application.domain.entity.Module;
import br.com.tecflix_app.modules.module.application.gateways.ModuleRepositoryGateway;
import br.com.tecflix_app.modules.shared.exception.general.ResourceNotFoundException;

import java.util.logging.Logger;

public class FindModuleByIdUseCaseImpl implements FindModuleByIdUseCase {
  private final Logger LOGGER = Logger.getLogger(FindModuleByIdUseCaseImpl.class.getName());

  private final ModuleRepositoryGateway moduleRepositoryGateway;

  public FindModuleByIdUseCaseImpl(ModuleRepositoryGateway moduleRepositoryGateway) {
    this.moduleRepositoryGateway = moduleRepositoryGateway;
  }

  @Override
  public Module execute(Long id) {
    LOGGER.info("Finding module by id");
    return moduleRepositoryGateway
        .findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Nenhum módulo encontrado para este id"));
  }
}
