package br.com.tecflix_app.config;

import br.com.tecflix_app.modules.gender.application.usecases.FindGenderByIdUseCase;
import br.com.tecflix_app.modules.occupation.application.usecases.FindOccupationByIdUseCase;
import br.com.tecflix_app.modules.professorData.application.gateways.ProfessorDataRepositoryGateway;
import br.com.tecflix_app.modules.professorData.application.usecases.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProfessorDataConfig {

  @Bean
  public FindProfessorDataByUserIdUseCase findProfessorDataByUserIdUseCase(
      ProfessorDataRepositoryGateway professorDataRepositoryGateway) {
    return new FindProfessorDataByUserIdUseCaseImpl(professorDataRepositoryGateway);
  }

  @Bean
  public FindProfessorDataByPhoneNumberUseCase findProfessorDataByPhoneNumberUseCase(
      ProfessorDataRepositoryGateway professorDataRepositoryGateway) {
    return new FindProfessorDataByPhoneNumberUseCaseImpl(professorDataRepositoryGateway);
  }

  @Bean
  public FindProfessorDataProfileByUserIdUseCase findProfessorDataProfileByUserIdUseCase(
      ProfessorDataRepositoryGateway professorDataRepositoryGateway) {
    return new FindProfessorDataProfileByUserIdUseCaseImpl(professorDataRepositoryGateway);
  }

  @Bean
  public PrepareProfessorDataForCreationUseCase createProfessorDataUseCase(
      FindProfessorDataByPhoneNumberUseCase findProfessorDataByPhoneNumberUseCase,
      FindGenderByIdUseCase findGenderByIdUseCase,
      FindOccupationByIdUseCase findOccupationByIdUseCase) {
    return new PrepareProfessorDataForCreationUseCaseImpl(
        findProfessorDataByPhoneNumberUseCase, findGenderByIdUseCase, findOccupationByIdUseCase);
  }
}
