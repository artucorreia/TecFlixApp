package br.com.tecflix_app.modules.professorData.application.usecases;

import br.com.tecflix_app.modules.professorData.application.domain.entity.ProfessorData;

import java.util.UUID;

/**
 * Use case responsible for retrieving the user's settings data by user ID.
 *
 * <p>Unlike simple entity lookups, this use case leverages repository projections to assemble the
 * {@link ProfessorData} needed for composing a user's settings.
 *
 * @author arthurcorreia.dev@gmail.com
 */
public interface FindAuthenticatedUserProfessorDataByUserIdUseCase {

  /**
   * Finds and returns the {@link ProfessorData} profile associated with the given user ID.
   *
   * @param userId the unique identifier of the user
   * @return the {@link ProfessorData} enriched with projection data for user's settings composition
   * @throws br.com.tecflix_app.modules.shared.exception.general.ResourceNotFoundException if no
   *     professor data exists with the provided ID
   */
  ProfessorData execute(UUID userId);
}
