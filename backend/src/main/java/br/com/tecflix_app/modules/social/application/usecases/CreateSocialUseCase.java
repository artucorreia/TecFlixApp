package br.com.tecflix_app.modules.social.application.usecases;

import br.com.tecflix_app.modules.social.application.domain.entity.Social;
import br.com.tecflix_app.modules.user.application.domain.entity.User;

/**
 * Use case responsible for creating a new social entity associated with a user.
 *
 * <p>This use case persists the given {@link Social} and links it to the specified {@link User}.
 *
 * @author arthurcorreia.dev@gmail.com
 */
public interface CreateSocialUseCase {

  /**
   * Creates a new {@link Social} entry and associates it with the provided {@link User}.
   *
   * @param user the user who owns the social
   * @param social the social entity to be created
   */
  void execute(User user, Social social);
}
