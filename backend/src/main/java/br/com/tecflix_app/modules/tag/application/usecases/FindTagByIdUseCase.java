package br.com.tecflix_app.modules.tag.application.usecases;

import br.com.tecflix_app.modules.tag.application.domain.entity.Tag;

/**
 * Use case responsible for retrieving a tag by its unique identifier.
 *
 * <p>This use case provides access to a single {@link Tag} entity that matches the given ID.
 *
 * @author arthurcorreia.dev@gmail.com
 */
public interface FindTagByIdUseCase {

  /**
   * Finds and returns a {@link Tag} entity associated with the given ID.
   *
   * @param tagId the unique identifier of the tag
   * @return the {@link Tag} associated with the specified ID
   * @throws br.com.tecflix_app.modules.shared.exception.general.ResourceNotFoundException if no tag
   *     exists with the provided ID
   */
  Tag execute(Long tagId);
}
