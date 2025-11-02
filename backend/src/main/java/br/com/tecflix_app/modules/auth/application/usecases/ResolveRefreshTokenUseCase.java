package br.com.tecflix_app.modules.auth.application.usecases;

import br.com.tecflix_app.modules.user.application.domain.entity.User;

/**
 * Use case responsible for resolving and validating a refresh token to retrieve the associated
 * user.
 *
 * @author arthurcorreia.dev@gmail.com
 */
public interface ResolveRefreshTokenUseCase {

  /**
   * Resolves and validates the given refresh token, returning the associated {@link User}.
   *
   * @param token the refresh token to be validated and resolved
   * @return the {@link User} associated with the given token
   * @throws br.com.tecflix_app.modules.shared.exception.general.ResourceNotFoundException if no
   *     refresh token exists for provide tokenF
   * @throws br.com.tecflix_app.modules.shared.exception.auth.RefreshTokenException if the token has
   *     already expired
   */
  User execute(String token);
}
