package br.com.tecflix_app.modules.auth.application.usecases;

import br.com.tecflix_app.modules.auth.application.domain.entity.TokenJwt;

import java.util.UUID;

/**
 * Use case responsible for generating a new JWT access token for a given user.
 *
 * @author arthurcorreia.dev@gmail.com
 */
public interface GenerateTokenUseCase {

  /**
   * Generates and returns a new {@link TokenJwt} for the user with the given ID.
   *
   * @param userId the unique identifier of the user for whom the token will be generated
   * @return a {@link TokenJwt} containing the generated JWT and related metadata
   * @throws br.com.tecflix_app.modules.auth.application.domain.exception.JwtCreationTokenException
   *     if an error occurs during token creation
   */
  TokenJwt execute(UUID userId);
}
