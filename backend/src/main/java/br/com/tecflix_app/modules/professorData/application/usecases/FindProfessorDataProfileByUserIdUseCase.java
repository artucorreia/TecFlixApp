package br.com.tecflix_app.modules.professorData.application.usecases;

import br.com.tecflix_app.modules.professorData.application.domain.entity.ProfessorData;

import java.util.UUID;

/**
 * Use case responsible for retrieving the professor's profile data by user ID.
 *
 * <p>Unlike simple entity lookups, this use case leverages repository projections to assemble the
 * {@link ProfessorData} needed for composing a professor's profile.
 *
 * @author arthurcorreia.dev@gmail.com
 */
public interface FindProfessorDataProfileByUserIdUseCase {

  /**
   * Finds and returns the {@link ProfessorData} profile associated with the given user ID.
   *
   * @param userId the unique identifier of the user
   * @return the {@link ProfessorData} enriched with projection data for profile composition
   * @throws br.com.tecflix_app.modules.shared.exception.general.ResourceNotFoundException if no
   *     professor profile exists with the provided ID
   */
  ProfessorData execute(UUID userId);
}
