package br.com.tecflix_app.modules.module.application.usecases;

import br.com.tecflix_app.modules.course.application.usecases.FindCourseByIdUseCase;
import br.com.tecflix_app.modules.module.application.domain.entity.Module;
import br.com.tecflix_app.modules.module.application.gateways.ModuleRepositoryGateway;

import java.util.Set;
import java.util.UUID;
import java.util.logging.Logger;

public class FindModulesByCourseIdUseCaseImpl implements FindModulesByCourseIdUseCase {
  private final Logger LOGGER = Logger.getLogger(FindModulesByCourseIdUseCaseImpl.class.getName());
  private final ModuleRepositoryGateway moduleRepositoryGateway;
  private final FindCourseByIdUseCase findCourseByIdUseCase;

  public FindModulesByCourseIdUseCaseImpl(
      ModuleRepositoryGateway moduleRepositoryGateway,
      FindCourseByIdUseCase findCourseByIdUseCase) {
    this.moduleRepositoryGateway = moduleRepositoryGateway;
    this.findCourseByIdUseCase = findCourseByIdUseCase;
  }

  @Override
  public Set<Module> execute(UUID courseId) {
    findCourseByIdUseCase.execute(courseId);
    LOGGER.info("Finding modules by course id: " + courseId.toString());
    return Set.copyOf(moduleRepositoryGateway.findByCourseId(courseId));
  }
}
