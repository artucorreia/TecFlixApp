package br.com.tecflix_app.modules.tag.application.usecases;

import br.com.tecflix_app.modules.tag.application.domain.entity.Tag;
import br.com.tecflix_app.modules.tag.application.gateways.TagRepositoryGateway;
import java.util.List;
import java.util.logging.Logger;

public class FindAllTagsCaseImpl implements FindAllTagsCase {
  private final Logger LOGGER = Logger.getLogger(FindAllTagsCaseImpl.class.getName());
  private final TagRepositoryGateway tagRepositoryGateway;

  public FindAllTagsCaseImpl(TagRepositoryGateway tagRepositoryGateway) {
    this.tagRepositoryGateway = tagRepositoryGateway;
  }

  @Override
  public List<Tag> execute() {
    LOGGER.info("Finding all tags");
    return tagRepositoryGateway.findAll();
  }
}
