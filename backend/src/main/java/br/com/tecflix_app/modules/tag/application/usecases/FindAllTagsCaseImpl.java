package br.com.tecflix_app.modules.tag.application.usecases;

import br.com.tecflix_app.modules.tag.application.domain.entity.Tag;
import br.com.tecflix_app.modules.tag.application.gateways.TagGateway;
import java.util.List;
import java.util.logging.Logger;

public class FindAllTagsCaseImpl implements FindAllTagsCase {
  private final Logger LOGGER = Logger.getLogger(FindAllTagsCaseImpl.class.getName());
  private final TagGateway tagGateway;

  public FindAllTagsCaseImpl(TagGateway tagGateway) {
    this.tagGateway = tagGateway;
  }

  @Override
  public List<Tag> execute() {
    LOGGER.info("Finding all tags");
    return tagGateway.findAll();
  }
}
