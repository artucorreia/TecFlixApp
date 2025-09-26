package br.com.tecflix_app.modules.courseClass.application.usecases;

import br.com.tecflix_app.modules.courseClass.application.domain.entity.Class;
import br.com.tecflix_app.modules.courseClass.application.gateways.ClassRepositoryGateway;
import br.com.tecflix_app.modules.module.application.usecases.FindModuleByIdUseCase;

import java.util.Set;
import java.util.logging.Logger;

public class FindClassesByModuleIdUseCaseImpl implements FindClassesByModuleIdUseCase {
  private final Logger LOGGER = Logger.getLogger(FindClassesByModuleIdUseCaseImpl.class.getName());
  private final ClassRepositoryGateway classRepositoryGateway;
  private final FindModuleByIdUseCase findModuleByIdUseCase;

  public FindClassesByModuleIdUseCaseImpl(
      ClassRepositoryGateway classRepositoryGateway, FindModuleByIdUseCase findModuleByIdUseCase) {
    this.classRepositoryGateway = classRepositoryGateway;
    this.findModuleByIdUseCase = findModuleByIdUseCase;
  }

  @Override
  public Set<Class> execute(Long moduleId) {
    findModuleByIdUseCase.execute(moduleId);
    LOGGER.info("Finding classes by module id: " + moduleId);
    return Set.copyOf(classRepositoryGateway.findByModuleId(moduleId));
  }
}
