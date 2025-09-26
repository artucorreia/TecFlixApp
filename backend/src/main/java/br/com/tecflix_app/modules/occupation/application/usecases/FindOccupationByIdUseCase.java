package br.com.tecflix_app.modules.occupation.application.usecases;

import br.com.tecflix_app.modules.occupation.application.domain.entity.Occupation;

/**
 * Use case responsible for retrieving occupation information by its unique identifier.
 *
 * <p>This use case returns an {@link Occupation} entity that matches the provided ID.
 *
 * @author arthurcorreia.dev@gmail.com
 */
public interface FindOccupationByIdUseCase {

  /**
   * Finds and returns an {@link Occupation} entity associated with the given ID.
   *
   * @param id the unique identifier of the occupation
   * @return the {@link Occupation} entity linked to the provided ID
   * @throws br.com.tecflix_app.modules.shared.exception.general.ResourceNotFoundException if no
   *     occupation exists with the given ID
   */
  Occupation execute(Long id);
}
