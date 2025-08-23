package br.com.tecflix_app.modules.tag.application.usecases;

import br.com.tecflix_app.modules.tag.application.domain.entity.Tag;

import java.util.List;

public interface FindAllTagsByIdUseCase {
  List<Tag> execute(List<Long> ids);
}
