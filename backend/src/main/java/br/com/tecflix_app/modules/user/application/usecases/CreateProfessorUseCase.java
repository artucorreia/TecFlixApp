package br.com.tecflix_app.modules.user.application.usecases;

import br.com.tecflix_app.modules.professorData.application.domain.entity.ProfessorData;
import br.com.tecflix_app.modules.social.application.domain.entity.Social;

import java.util.Set;

/**
 * Use case responsible for creating a new professor profile with the provided data and socials.
 *
 * <p>This use case persists the given {@link ProfessorData} and its associated {@link Social}
 * entities into the system.
 *
 * @author arthurcorreia.dev@gmail.com
 */
public interface CreateProfessorUseCase {

  /**
   * Creates a new professor profile using the provided data and associated socials.
   *
   * @param professorData the core information about the professor to be created
   * @param socials the set of {@link Social} entities linked to the professor
   * @throws br.com.tecflix_app.modules.shared.exception.general.ResourceNotFoundException if an
   *     error occurred while searching for the logged-in user
   * @throws br.com.tecflix_app.modules.shared.exception.general.ActionNotAllowedException if the
   *     logged-in user already has a professor registration
   */
  void execute(ProfessorData professorData, Set<Social> socials);
}
