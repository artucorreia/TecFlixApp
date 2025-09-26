package br.com.tecflix_app.modules.gender.application.usecases;

import br.com.tecflix_app.modules.gender.application.domain.entity.Gender;

/**
 * Use case responsible for retrieving gender information by its unique identifier.
 *
 * <p>This use case returns a {@link Gender} entity that matches the provided ID.
 *
 * @author arthurcorreia.dev@gmail.com
 */
public interface FindGenderByIdUseCase {

  /**
   * Finds and returns a {@link Gender} entity associated with the given ID.
   *
   * @param id the unique identifier of the gender
   * @return the {@link Gender} entity linked to the provided ID
   * @throws br.com.tecflix_app.modules.shared.exception.general.ResourceNotFoundException if no
   *     gender exists with the given ID
   */
  Gender execute(Long id);
}
