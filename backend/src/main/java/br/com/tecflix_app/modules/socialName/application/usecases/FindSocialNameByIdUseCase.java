package br.com.tecflix_app.modules.socialName.application.usecases;

import br.com.tecflix_app.modules.socialName.application.domain.entity.SocialName;

/**
 * Use case responsible for retrieving a social name by its unique identifier.
 *
 * <p>This use case returns a {@link SocialName} entity that matches the provided ID.
 *
 * @author arthurcorreia.dev@gmail.com
 */
public interface FindSocialNameByIdUseCase {

  /**
   * Finds and returns a {@link SocialName} entity associated with the given ID.
   *
   * @param id the unique identifier of the social name
   * @return the {@link SocialName} entity linked to the provided ID
   * @throws br.com.tecflix_app.modules.shared.exception.general.ResourceNotFoundException if no
   *     social name exists with the given ID
   */
  SocialName execute(Long id);
}
