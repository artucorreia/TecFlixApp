package br.com.tecflix_app.modules.professorData.application.usecases;

import br.com.tecflix_app.modules.gender.application.domain.entity.Gender;
import br.com.tecflix_app.modules.gender.application.usecases.FindGenderByIdUseCase;
import br.com.tecflix_app.modules.occupation.application.domain.entity.Occupation;
import br.com.tecflix_app.modules.occupation.application.usecases.FindOccupationByIdUseCase;
import br.com.tecflix_app.modules.professorData.application.domain.entity.ProfessorData;
import br.com.tecflix_app.modules.shared.exception.general.RepeatedDataException;
import br.com.tecflix_app.modules.user.application.domain.entity.User;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.logging.Logger;

public class PrepareProfessorDataForCreationUseCaseImpl implements PrepareProfessorDataForCreationUseCase {
  private final Logger LOGGER = Logger.getLogger(PrepareProfessorDataForCreationUseCaseImpl.class.getName());
  private final FindProfessorDataByPhoneNumberUseCase findProfessorDataByPhoneNumberUseCase;
  private final FindGenderByIdUseCase findGenderByIdUseCase;
  private final FindOccupationByIdUseCase findOccupationByIdUseCase;

  public PrepareProfessorDataForCreationUseCaseImpl(
      FindProfessorDataByPhoneNumberUseCase findProfessorDataByPhoneNumberUseCase,
      FindGenderByIdUseCase findGenderByIdUseCase,
      FindOccupationByIdUseCase findOccupationByIdUseCase) {
    this.findProfessorDataByPhoneNumberUseCase = findProfessorDataByPhoneNumberUseCase;
    this.findGenderByIdUseCase = findGenderByIdUseCase;
    this.findOccupationByIdUseCase = findOccupationByIdUseCase;
  }

  @Override
  public ProfessorData execute(User user, ProfessorData professorData) {
    LOGGER.info("Preparing professor data");
    Optional<ProfessorData> optionalProfessorData =
        findProfessorDataByPhoneNumberUseCase.execute(professorData.getPhoneNumber().trim());
    if (optionalProfessorData.isPresent())
      throw new RepeatedDataException("Esse número de telefone já está em uso");

    Occupation occupation =
        findOccupationByIdUseCase.execute(professorData.getOccupation().getId());
    professorData.setOccupation(occupation);
    Gender gender = findGenderByIdUseCase.execute(professorData.getGender().getId());
    professorData.setGender(gender);

    professorData.setUser(user);
    professorData.setCreatedAt(LocalDateTime.now());
    professorData.setDeleted(false);
    professorData.setPhoneNumber(professorData.getPhoneNumber().trim());
    professorData.setBiography(professorData.getBiography().trim());
    professorData.setProfileImageUrl(professorData.getProfileImageUrl().trim());
    return professorData;
  }
}
