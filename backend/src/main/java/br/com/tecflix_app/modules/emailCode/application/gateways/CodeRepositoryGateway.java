package br.com.tecflix_app.modules.emailCode.application.gateways;

import br.com.tecflix_app.modules.emailCode.application.domain.entity.EmailCode;

import java.util.UUID;

/**
 * Gateway interface for managing {@link EmailCode} persistence.
 *
 * <p>This interface defines the contract for saving and deleting email codes, abstracting the
 * underlying data storage mechanism.
 */
public interface CodeRepositoryGateway {

  /**
   * Saves a new or updated {@link EmailCode} entity to the repository.
   *
   * @param code The {@link EmailCode} entity to be saved.
   * @return The saved {@link EmailCode} entity.
   */
  EmailCode save(EmailCode code);

  /**
   * Deletes all email codes associated with a specific user.
   *
   * @param userId The unique identifier of the user whose codes should be deleted.
   */
  void deleteByUserId(UUID userId);
}
