package br.com.tecflix_app.modules.tag.infra.presentation.mapper;

import br.com.tecflix_app.modules.tag.application.domain.entity.Tag;
import br.com.tecflix_app.modules.tag.infra.presentation.dtos.v1.response.TagResponseDTO;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TagPresentationMapper {
  TagResponseDTO toResponse(Tag tag);

  List<TagResponseDTO> toResponse(List<Tag> tag);
}
