package br.com.tecflix_app.modules.tag.infra.presentation.mapper;

import br.com.tecflix_app.modules.tag.application.domain.entity.Tag;
import br.com.tecflix_app.modules.tag.infra.presentation.dtos.v1.response.TagDTOResponse;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TagPresentationMapper {
  TagDTOResponse toResponse(Tag tag);

  List<TagDTOResponse> toResponse(List<Tag> tag);
}
