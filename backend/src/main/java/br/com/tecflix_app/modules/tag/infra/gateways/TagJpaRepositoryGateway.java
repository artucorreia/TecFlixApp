package br.com.tecflix_app.modules.tag.infra.gateways;

import br.com.tecflix_app.modules.tag.application.domain.entity.Tag;
import br.com.tecflix_app.modules.tag.application.gateways.TagGateway;
import br.com.tecflix_app.modules.tag.infra.gateways.mapper.TagGatewayMapper;
import br.com.tecflix_app.modules.tag.infra.persistence.TagEntity;
import br.com.tecflix_app.modules.tag.infra.persistence.TagRepository;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TagJpaRepositoryGateway implements TagGateway {

  private final TagRepository tagRepository;
  private final TagGatewayMapper tagGatewayMapper;

  @Override
  public Optional<Tag> findById(Long tagId) {
    Optional<TagEntity> tagEntity = tagRepository.findById(tagId);
    return tagEntity.map(tagGatewayMapper::toDomain);
  }

  @Override
  public List<Tag> findAll() {
    List<TagEntity> tagEntities = tagRepository.findAll();
    return tagEntities.stream().map(tagGatewayMapper::toDomain).toList();
  }
}
