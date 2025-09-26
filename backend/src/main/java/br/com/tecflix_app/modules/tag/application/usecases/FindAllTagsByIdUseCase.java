package br.com.tecflix_app.modules.tag.application.usecases;

import br.com.tecflix_app.modules.tag.application.domain.entity.Tag;

import java.util.List;

/**
 * Use case responsible for retrieving multiple tags by their unique identifiers.
 *
 * <p>This use case provides access to a list of {@link Tag} entities that correspond to the given
 * set of tag IDs.
 *
 * @author arthurcorreia.dev@gmail.com
 */
public interface FindAllTagsByIdUseCase {

  /**
   * Finds and returns all {@link Tag} entities associated with the provided list of IDs.
   *
   * @param ids the list of unique identifiers of the tags
   * @return a {@link List} containing all tags that match the specified IDs
   */
  List<Tag> execute(List<Long> ids);
}
