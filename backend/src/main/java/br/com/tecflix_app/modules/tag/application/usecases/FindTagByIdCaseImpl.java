package br.com.tecflix_app.modules.tag.application.usecases;

import br.com.tecflix_app.modules.tag.application.domain.entity.Tag;
import br.com.tecflix_app.modules.tag.application.gateways.TagGateway;
import br.com.tecflix_app.modules.shared.exception.general.ResourceNotFoundException;
import java.util.Optional;
import java.util.logging.Logger;

public class FindTagByIdCaseImpl implements FindTagByIdCase {
  private final Logger LOGGER = Logger.getLogger(FindTagByIdCaseImpl.class.getName());
  private final TagGateway tagGateway;

  public FindTagByIdCaseImpl(TagGateway tagGateway) {
    this.tagGateway = tagGateway;
  }

  @Override
  public Tag execute(Long tagId) {
    LOGGER.info("Finding tag by id");
    Optional<Tag> optionalTag = tagGateway.findById(tagId);
    if (optionalTag.isEmpty())
      throw new ResourceNotFoundException("Nenhuma tag encontrada para este id");
    return optionalTag.get();
  }
}
