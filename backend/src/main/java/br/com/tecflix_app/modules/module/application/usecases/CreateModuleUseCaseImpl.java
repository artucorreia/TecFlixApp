package br.com.tecflix_app.modules.module.application.usecases;

import br.com.tecflix_app.modules.auth.application.gateways.AuthenticatedUserGateway;
import br.com.tecflix_app.modules.course.application.domain.entity.Course;
import br.com.tecflix_app.modules.course.application.usecases.FindCourseByIdUseCase;
import br.com.tecflix_app.modules.module.application.domain.entity.Module;
import br.com.tecflix_app.modules.module.application.gateways.ModuleRepositoryGateway;
import br.com.tecflix_app.modules.shared.exception.general.ActionNotAllowedException;
import br.com.tecflix_app.modules.shared.exception.general.ResourceNotFoundException;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.logging.Logger;

public class CreateModuleUseCaseImpl implements CreateModuleUseCase {
  private final Logger LOGGER = Logger.getLogger(CreateModuleUseCaseImpl.class.getName());

  private final ModuleRepositoryGateway moduleRepositoryGateway;
  private final FindCourseByIdUseCase findCourseByIdUseCase;
  private final AuthenticatedUserGateway authenticatedUserGateway;

  public CreateModuleUseCaseImpl(
      ModuleRepositoryGateway moduleRepositoryGateway,
      FindCourseByIdUseCase findCourseByIdUseCase,
      AuthenticatedUserGateway authenticatedUserGateway) {
    this.moduleRepositoryGateway = moduleRepositoryGateway;
    this.findCourseByIdUseCase = findCourseByIdUseCase;
    this.authenticatedUserGateway = authenticatedUserGateway;
  }

  @Override
  public void execute(Module module) {
    LOGGER.info("Creating a new module to course: " + module.getCourse().getId());
    Course course = findCourseByIdUseCase.execute(module.getCourse().getId());

    boolean loggedUserIsCourseProfessor =
        checkIfLoggedUserIsCourseProfessor(course.getProfessor().getId());
    if (!loggedUserIsCourseProfessor)
      throw new ActionNotAllowedException("Curso não pertence ao usuário logado");

    module.setTitle(module.getTitle().trim());
    module.setCourse(course);
    module.setCreatedAt(LocalDateTime.now());
    module.setActive(true);
    moduleRepositoryGateway.save(module);
  }

  private boolean checkIfLoggedUserIsCourseProfessor(UUID courseProfessorId) {
    UUID loggedUserId =
        authenticatedUserGateway
            .findId()
            .orElseThrow(() -> new ResourceNotFoundException("Erro ao resgatar usuário logado"));

    return loggedUserId.equals(courseProfessorId);
  }
}
