package br.com.tecflix_app.modules.user.application.usecases;

import br.com.tecflix_app.modules.auth.application.gateways.AuthenticatedUserGateway;
import br.com.tecflix_app.modules.professorData.application.domain.entity.ProfessorData;
import br.com.tecflix_app.modules.professorData.application.usecases.PrepareProfessorDataForCreationUseCase;
import br.com.tecflix_app.modules.professorData.application.usecases.FindProfessorDataByUserIdUseCase;
import br.com.tecflix_app.modules.role.application.domain.entity.Role;
import br.com.tecflix_app.modules.role.application.gateways.RoleRepositoryGateway;
import br.com.tecflix_app.modules.shared.exception.general.ActionNotAllowedException;
import br.com.tecflix_app.modules.shared.exception.general.ResourceNotFoundException;
import br.com.tecflix_app.modules.social.application.domain.entity.Social;
import br.com.tecflix_app.modules.social.application.usecases.PrepareAllSocialsForCreationUseCase;
import br.com.tecflix_app.modules.user.application.domain.entity.User;
import br.com.tecflix_app.modules.user.application.gateways.ProfessorRegistrationRepositoryGateway;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Logger;

public class CreateProfessorUseCaseImpl implements CreateProfessorUseCase {
  private final Logger LOGGER = Logger.getLogger(CreateProfessorUseCaseImpl.class.getName());
  private final ProfessorRegistrationRepositoryGateway professorRegistrationRepositoryGateway;
  private final FindUserByIdUseCase findUserByIdUseCase;
  private final AuthenticatedUserGateway authenticatedUserGateway;
  private final FindProfessorDataByUserIdUseCase findProfessorDataByUserIdUseCase;
  private final PrepareProfessorDataForCreationUseCase prepareProfessorDataForCreationUseCase;
  private final PrepareAllSocialsForCreationUseCase prepareAllSocialsForCreationUseCase;
  private final RoleRepositoryGateway roleRepositoryGateway;

  public CreateProfessorUseCaseImpl(
      ProfessorRegistrationRepositoryGateway professorRegistrationRepositoryGateway,
      FindUserByIdUseCase findUserByIdUseCase,
      AuthenticatedUserGateway authenticatedUserGateway,
      FindProfessorDataByUserIdUseCase findProfessorDataByUserIdUseCase,
      PrepareProfessorDataForCreationUseCase prepareProfessorDataForCreationUseCase,
      PrepareAllSocialsForCreationUseCase prepareAllSocialsForCreationUseCase,
      RoleRepositoryGateway roleRepositoryGateway) {
    this.professorRegistrationRepositoryGateway = professorRegistrationRepositoryGateway;
    this.findUserByIdUseCase = findUserByIdUseCase;
    this.authenticatedUserGateway = authenticatedUserGateway;
    this.findProfessorDataByUserIdUseCase = findProfessorDataByUserIdUseCase;
    this.prepareProfessorDataForCreationUseCase = prepareProfessorDataForCreationUseCase;
    this.prepareAllSocialsForCreationUseCase = prepareAllSocialsForCreationUseCase;
    this.roleRepositoryGateway = roleRepositoryGateway;
  }

  @Override
  public void execute(ProfessorData professorData, Set<Social> socials) {
    LOGGER.info("Registering a user as professor");
    UUID authenticatedUserId =
        authenticatedUserGateway
            .findId()
            .orElseThrow(() -> new ResourceNotFoundException("Erro ao resgatar usuário logado"));
    User user = findUserByIdUseCase.execute(authenticatedUserId);
    Optional<ProfessorData> optionalProfessorData =
        findProfessorDataByUserIdUseCase.execute(authenticatedUserId);
    if (optionalProfessorData.isPresent())
      throw new ActionNotAllowedException("O usuário já possui cadastro de professor");

    ProfessorData preparedProfessorData =
        prepareProfessorDataForCreationUseCase.execute(user, professorData);
    Set<Social> preparedSocials = prepareAllSocialsForCreationUseCase.execute(user, socials);
    List<Role> rolesToAdd = roleRepositoryGateway.findByNameInIgnoreCase(List.of("PROFESSOR"));
    user.getRoles().addAll(rolesToAdd);

    professorRegistrationRepositoryGateway.save(
        user, preparedProfessorData, List.copyOf(preparedSocials));
  }
}
