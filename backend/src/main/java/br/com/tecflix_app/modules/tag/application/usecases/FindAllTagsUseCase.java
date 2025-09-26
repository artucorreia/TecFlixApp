package br.com.tecflix_app.modules.tag.application.usecases;

import br.com.tecflix_app.modules.tag.application.domain.entity.Tag;
import java.util.Set;

public interface FindAllTagsUseCase {
  Set<Tag> execute();
}
