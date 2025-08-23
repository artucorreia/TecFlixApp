package br.com.tecflix_app.modules.tag.application.usecases;

import br.com.tecflix_app.modules.tag.application.domain.entity.Tag;
import br.com.tecflix_app.modules.tag.application.gateways.TagRepositoryGateway;

import java.util.List;
import java.util.logging.Logger;

public class FindAllTagsByIdUseCaseImpl implements FindAllTagsByIdUseCase {
  private final Logger LOGGER = Logger.getLogger(FindAllTagsByIdUseCaseImpl.class.getName());
  private final TagRepositoryGateway tagRepositoryGateway;

  public FindAllTagsByIdUseCaseImpl(TagRepositoryGateway tagRepositoryGateway) {
    this.tagRepositoryGateway = tagRepositoryGateway;
  }

  @Override
  public List<Tag> execute(List<Long> ids) {
    LOGGER.info("Finding all tags by id");
    return tagRepositoryGateway.findAllById(ids);
  }
}
