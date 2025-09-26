package br.com.tecflix_app.modules.tag.application.usecases;

import br.com.tecflix_app.modules.tag.application.domain.entity.Tag;
import br.com.tecflix_app.modules.tag.application.gateways.TagRepositoryGateway;

import java.util.Set;
import java.util.logging.Logger;

public class FindAllTagsUseCaseImpl implements FindAllTagsUseCase {
  private final Logger LOGGER = Logger.getLogger(FindAllTagsUseCaseImpl.class.getName());
  private final TagRepositoryGateway tagRepositoryGateway;

  public FindAllTagsUseCaseImpl(TagRepositoryGateway tagRepositoryGateway) {
    this.tagRepositoryGateway = tagRepositoryGateway;
  }

  @Override
  public Set<Tag> execute() {
    LOGGER.info("Finding all tags");
    return Set.copyOf(tagRepositoryGateway.findAll());
  }
}
