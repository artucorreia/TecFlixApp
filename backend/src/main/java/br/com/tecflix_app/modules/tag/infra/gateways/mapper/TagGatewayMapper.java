package br.com.tecflix_app.modules.tag.infra.gateways.mapper;

import br.com.tecflix_app.modules.tag.application.domain.entity.Tag;
import br.com.tecflix_app.modules.tag.infra.persistence.TagEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TagGatewayMapper {

  TagEntity toEntity(Tag tag);

  Tag toDomain(TagEntity tagEntity);
}
