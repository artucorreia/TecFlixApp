package br.com.tecflix_app.modules.social.application.usecases;

import br.com.tecflix_app.modules.social.application.domain.entity.Social;

import java.util.List;
import java.util.UUID;

/**
 * Use case responsible for retrieving all social entities associated with a user's settings.
 *
 * <p>This use case leverages repository projections to gather the {@link Social} data that
 * contributes to building user's settings page.
 *
 * @author arthurcorreia.dev@gmail.com
 */
public interface FindSocialsAuthenticatedUserByUserIdUseCase {

  /**
   * Finds and returns all {@link Social} entities associated with the given user ID, enriched for
   * user settings composition.
   *
   * @param userId the unique identifier of the user
   * @return a {@link List} containing all socials linked to the user's settings
   */
  List<Social> execute(UUID userId);
}
