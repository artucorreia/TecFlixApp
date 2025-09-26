package br.com.tecflix_app.modules.professorData.application.usecases;

import br.com.tecflix_app.modules.professorData.application.domain.entity.ProfessorData;
import br.com.tecflix_app.modules.user.application.domain.entity.User;

/**
 * Use case responsible for preparing professor data before creation.
 *
 * <p>This use case applies preprocessing logic such as setting default values, enriching
 * information based on the provided {@link User}, and ensuring that the {@link ProfessorData}
 * object is ready to be persisted.
 *
 * @author arthurcorreia.dev@gmail.com
 */
public interface PrepareProfessorDataForCreationUseCase {

  /**
   * Prepares and returns a {@link ProfessorData} object for creation by applying default values and
   * user-specific adjustments.
   *
   * @param user the {@link User} associated with the professor
   * @param professorData the raw professor data provided for creation
   * @return a prepared {@link ProfessorData} instance ready for persistence
   */
  ProfessorData execute(User user, ProfessorData professorData);
}
