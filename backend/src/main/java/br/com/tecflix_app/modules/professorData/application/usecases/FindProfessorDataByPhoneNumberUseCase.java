package br.com.tecflix_app.modules.professorData.application.usecases;

import br.com.tecflix_app.modules.professorData.application.domain.entity.ProfessorData;

import java.util.Optional;

/**
 * Use case responsible for retrieving professor data by phone number.
 *
 * <p>This use case attempts to find a {@link ProfessorData} entity associated with the provided
 * phone number.
 *
 * @author arthurcorreia.dev@gmail.com
 */
public interface FindProfessorDataByPhoneNumberUseCase {

  /**
   * Finds and returns the {@link ProfessorData} entity associated with the given phone number.
   *
   * @param phoneNumber the professor's phone number
   * @return an {@link Optional} containing the professor data if found, or empty if no match exists
   */
  Optional<ProfessorData> execute(String phoneNumber);
}
