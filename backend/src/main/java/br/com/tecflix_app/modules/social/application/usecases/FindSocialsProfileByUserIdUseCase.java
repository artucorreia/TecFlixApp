package br.com.tecflix_app.modules.social.application.usecases;

import br.com.tecflix_app.modules.social.application.domain.entity.Social;

import java.util.List;
import java.util.UUID;

/**
 * Use case responsible for retrieving all social entities associated with a user's profile.
 *
 * <p>This use case leverages repository projections to gather the {@link Social} data that
 * contributes to building a professor's profile.
 *
 * @author arthurcorreia.dev@gmail.com
 */
public interface FindSocialsProfileByUserIdUseCase {

  /**
   * Finds and returns all {@link Social} entities associated with the given user ID, enriched for
   * profile composition.
   *
   * @param userId the unique identifier of the user
   * @return a {@link List} containing all socials linked to the user's profile
   */
  List<Social> execute(UUID userId);
}
