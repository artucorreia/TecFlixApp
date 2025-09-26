package br.com.tecflix_app.modules.professorData.application.usecases;

import br.com.tecflix_app.modules.professorData.application.domain.entity.ProfessorData;

import java.util.Optional;
import java.util.UUID;

/**
 * Use case responsible for retrieving professor data associated with a given user.
 *
 * <p>This use case returns the {@link ProfessorData} tied to a specific user, identified by their
 * unique ID.
 *
 * @author arthurcorreia.dev@gmail.com
 */
public interface FindProfessorDataByUserIdUseCase {

  /**
   * Finds and returns the {@link ProfessorData} associated with the given user ID.
   *
   * @param userId the unique identifier of the user
   * @return an {@link Optional} containing the professor data if found, or empty if not found
   */
  Optional<ProfessorData> execute(UUID userId);
}
