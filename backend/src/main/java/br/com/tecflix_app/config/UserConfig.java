package br.com.tecflix_app.config;

import br.com.tecflix_app.modules.auth.application.gateways.AuthenticatedUserGateway;
import br.com.tecflix_app.modules.emailCode.application.gateways.CodeSenderGateway;
import br.com.tecflix_app.modules.emailCode.application.usecases.CreateCodeUseCase;
import br.com.tecflix_app.modules.professorData.application.usecases.PrepareProfessorDataForCreationUseCase;
import br.com.tecflix_app.modules.professorData.application.usecases.FindProfessorDataByUserIdUseCase;
import br.com.tecflix_app.modules.role.application.gateways.RoleRepositoryGateway;
import br.com.tecflix_app.modules.social.application.usecases.PrepareAllSocialsForCreationUseCase;
import br.com.tecflix_app.modules.user.application.gateways.ProfessorRegistrationRepositoryGateway;
import br.com.tecflix_app.modules.user.application.gateways.UserRepositoryGateway;
import br.com.tecflix_app.modules.user.application.usecases.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

  @Bean
  public FindMeUseCase findMeUseCase(AuthenticatedUserGateway authenticatedUserGateway) {
    return new FindMeUseCaseImpl(authenticatedUserGateway);
  }

  @Bean
  public FindUserByIdUseCase findUserByIdUseCase(UserRepositoryGateway userRepositoryGateway) {
    return new FindUserByIdUseCaseImpl(userRepositoryGateway);
  }

  @Bean
  public FindUserByEmailUseCase findUserByEmailUseCase(
      UserRepositoryGateway userRepositoryGateway) {
    return new FindUserByEmailUseCaseImpl(userRepositoryGateway);
  }

  @Bean
  public RegisterUserUseCase registerUserUseCase(
      UserRepositoryGateway userRepositoryGateway,
      CreateCodeUseCase createCodeUseCase,
      RoleRepositoryGateway roleRepositoryGateway,
      CodeSenderGateway codeSenderGateway) {
    return new RegisterUserUseCaseImpl(
        userRepositoryGateway,
        createCodeUseCase,
        roleRepositoryGateway,
        codeSenderGateway);
  }

  @Bean
  public CreateProfessorUseCase createProfessorUseCase(
      ProfessorRegistrationRepositoryGateway professorRegistrationRepositoryGateway,
      FindUserByIdUseCase findUserByIdUseCase,
      AuthenticatedUserGateway authenticatedUserGateway,
      FindProfessorDataByUserIdUseCase findProfessorDataByUserIdUseCase,
      PrepareProfessorDataForCreationUseCase prepareProfessorDataForCreationUseCase,
      PrepareAllSocialsForCreationUseCase prepareAllSocialsForCreationUseCase,
      RoleRepositoryGateway roleRepositoryGateway) {
    return new CreateProfessorUseCaseImpl(
        professorRegistrationRepositoryGateway,
        findUserByIdUseCase,
        authenticatedUserGateway,
        findProfessorDataByUserIdUseCase,
        prepareProfessorDataForCreationUseCase,
        prepareAllSocialsForCreationUseCase,
        roleRepositoryGateway);
  }
}
