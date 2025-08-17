package br.com.tecflix_app.modules.tag.infra.presentation.mapper;

import br.com.tecflix_app.modules.tag.application.domain.entity.Tag;
import br.com.tecflix_app.modules.tag.infra.dtos.v1.response.TagResponse;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TagPresentationMapper {
  TagResponse toResponse(Tag tag);

  List<TagResponse> toResponse(List<Tag> tag);
}
