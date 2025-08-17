package br.com.tecflix_app.modules.tag.application.usecases;

import br.com.tecflix_app.modules.tag.application.domain.entity.Tag;

public interface FindTagByIdCase {
  Tag execute(Long tagId);
}
