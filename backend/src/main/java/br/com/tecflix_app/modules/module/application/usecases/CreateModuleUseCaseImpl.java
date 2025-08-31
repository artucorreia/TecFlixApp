package br.com.tecflix_app.modules.module.application.usecases;

import br.com.tecflix_app.modules.course.application.domain.entity.Course;
import br.com.tecflix_app.modules.course.application.usecases.FindCourseByIdUseCase;
import br.com.tecflix_app.modules.module.application.domain.entity.Module;
import br.com.tecflix_app.modules.module.application.gateways.ModuleRepositoryGateway;

import java.time.LocalDateTime;
import java.util.logging.Logger;

public class CreateModuleUseCaseImpl implements CreateModuleUseCase {
  private final Logger LOGGER = Logger.getLogger(CreateModuleUseCaseImpl.class.getName());

  private final ModuleRepositoryGateway moduleRepositoryGateway;
  private final FindCourseByIdUseCase findCourseByIdUseCase;

  public CreateModuleUseCaseImpl(
      ModuleRepositoryGateway moduleRepositoryGateway,
      FindCourseByIdUseCase findCourseByIdUseCase) {
    this.moduleRepositoryGateway = moduleRepositoryGateway;
    this.findCourseByIdUseCase = findCourseByIdUseCase;
  }

  @Override
  public void execute(Module module) {
    LOGGER.info("Creating a new module to course: " + module.getCourse().getId());
    Course course = findCourseByIdUseCase.execute(module.getCourse().getId());
    module.setTitle(module.getTitle().trim());
    module.setCourse(course);
    module.setCreatedAt(LocalDateTime.now());
    module.setActive(true);
    moduleRepositoryGateway.save(module);
  }
}
