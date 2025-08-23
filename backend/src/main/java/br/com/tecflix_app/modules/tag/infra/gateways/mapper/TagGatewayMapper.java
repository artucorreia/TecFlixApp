package br.com.tecflix_app.modules.tag.infra.gateways.mapper;

import br.com.tecflix_app.modules.tag.application.domain.entity.Tag;
import br.com.tecflix_app.modules.tag.infra.persistence.TagEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TagGatewayMapper {

  TagEntity domainToEntity(Tag tag);

  List<Tag> entityToDomain(List<TagEntity> tagEntities);

  Tag entityToDomain(TagEntity tagEntity);
}
