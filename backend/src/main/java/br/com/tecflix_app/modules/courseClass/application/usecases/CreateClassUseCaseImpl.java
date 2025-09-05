package br.com.tecflix_app.modules.courseClass.application.usecases;

import br.com.tecflix_app.modules.auth.application.gateways.AuthenticatedUserGateway;
import br.com.tecflix_app.modules.courseClass.application.domain.entity.Class;
import br.com.tecflix_app.modules.courseClass.application.gateways.ClassRepositoryGateway;
import br.com.tecflix_app.modules.module.application.domain.entity.Module;
import br.com.tecflix_app.modules.module.application.usecases.FindModuleByIdUseCase;
import br.com.tecflix_app.modules.shared.exception.general.ActionNotAllowedException;
import br.com.tecflix_app.modules.shared.exception.general.ResourceNotFoundException;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.logging.Logger;

public class CreateClassUseCaseImpl implements CreateClassUseCase {
  private final Logger LOGGER = Logger.getLogger(CreateClassUseCaseImpl.class.getName());

  private final ClassRepositoryGateway classRepositoryGateway;
  private final FindModuleByIdUseCase findModuleByIdUseCase;
  private final AuthenticatedUserGateway authenticatedUserGateway;

  public CreateClassUseCaseImpl(
      ClassRepositoryGateway classRepositoryGateway,
      FindModuleByIdUseCase findModuleByIdUseCase,
      AuthenticatedUserGateway authenticatedUserGateway) {
    this.classRepositoryGateway = classRepositoryGateway;
    this.findModuleByIdUseCase = findModuleByIdUseCase;
    this.authenticatedUserGateway = authenticatedUserGateway;
  }

  @Override
  public void execute(Class courseClass) {
    LOGGER.info("Creating a new class to module: " + courseClass.getModule().getId());
    Module module = findModuleByIdUseCase.execute(courseClass.getModule().getId());

    boolean loggedUserIsCourseProfessor =
        checkIfLoggedUserIsCourseProfessor(module.getCourse().getProfessor().getId());
    if (!loggedUserIsCourseProfessor)
      throw new ActionNotAllowedException("Módulo não pertence ao usuário logado");

    courseClass.setTitle(courseClass.getTitle().trim());
    courseClass.setVideoPath(courseClass.getVideoPath().trim());
    courseClass.setModule(module);
    courseClass.setActive(true);
    courseClass.setCreatedAt(LocalDateTime.now());
    classRepositoryGateway.save(courseClass);
  }

  private boolean checkIfLoggedUserIsCourseProfessor(UUID courseProfessorId) {
    UUID loggedUserId =
        authenticatedUserGateway
            .findId()
            .orElseThrow(() -> new ResourceNotFoundException("Erro ao resgatar usuário logado"));

    return loggedUserId.equals(courseProfessorId);
  }
}
