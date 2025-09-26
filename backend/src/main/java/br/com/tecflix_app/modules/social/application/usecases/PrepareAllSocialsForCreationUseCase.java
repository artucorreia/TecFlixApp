package br.com.tecflix_app.modules.social.application.usecases;

import br.com.tecflix_app.modules.social.application.domain.entity.Social;
import br.com.tecflix_app.modules.user.application.domain.entity.User;

import java.util.Set;

/**
 * Use case responsible for preparing social data before creation.
 *
 * <p>This use case applies preprocessing logic such as setting default values, linking socials to
 * the provided {@link User}, and ensuring that the set of {@link Social} entities is ready to be
 * persisted.
 *
 * @author arthurcorreia.dev@gmail.com
 */
public interface PrepareAllSocialsForCreationUseCase {

  /**
   * Prepares and returns a set of {@link Social} entities for creation by applying default values
   * and user-specific adjustments.
   *
   * @param user the {@link User} to whom the socials will belong
   * @param socials the raw set of socials provided for creation
   * @return a prepared {@link Set} of {@link Social} entities ready for persistence
   */
  Set<Social> execute(User user, Set<Social> socials);
}
