package br.com.tecflix_app.modules.auth.application.usecases;

import br.com.tecflix_app.modules.user.application.domain.entity.User;

/**
 * Use case responsible for generating and associating a new refresh token for a user.
 *
 * @author arthurcorreia.dev@gmail.com
 */
public interface CreateRefreshTokenUseCase {

  /**
   * Generates and returns a new refresh token for the specified {@link User}.
   *
   * @param user the user for whom the refresh token will be created
   * @return a newly generated refresh token as a {@link String}
   */
  String execute(User user);
}
