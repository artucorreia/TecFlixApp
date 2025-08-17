package br.com.tecflix_app.modules.tag.application.gateways;

import br.com.tecflix_app.modules.tag.application.domain.entity.Tag;

import java.util.List;
import java.util.Optional;

public interface TagGateway {
  Optional<Tag> findById(Long tagId);

  List<Tag> findAll();
}
