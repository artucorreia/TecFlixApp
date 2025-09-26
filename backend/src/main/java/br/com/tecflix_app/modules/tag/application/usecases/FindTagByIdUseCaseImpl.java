package br.com.tecflix_app.modules.tag.application.usecases;

import br.com.tecflix_app.modules.tag.application.domain.entity.Tag;
import br.com.tecflix_app.modules.tag.application.gateways.TagRepositoryGateway;
import br.com.tecflix_app.modules.shared.exception.general.ResourceNotFoundException;
import java.util.Optional;
import java.util.logging.Logger;

public class FindTagByIdUseCaseImpl implements FindTagByIdUseCase {
  private final Logger LOGGER = Logger.getLogger(FindTagByIdUseCaseImpl.class.getName());
  private final TagRepositoryGateway tagRepositoryGateway;

  public FindTagByIdUseCaseImpl(TagRepositoryGateway tagRepositoryGateway) {
    this.tagRepositoryGateway = tagRepositoryGateway;
  }

  @Override
  public Tag execute(Long tagId) {
    LOGGER.info("Finding tag by id");
    Optional<Tag> optionalTag = tagRepositoryGateway.findById(tagId);
    if (optionalTag.isEmpty())
      throw new ResourceNotFoundException("Nenhuma tag encontrada para este id");
    return optionalTag.get();
  }
}
