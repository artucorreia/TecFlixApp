package br.com.tecflix_app.modules.tag.infra.presentation.mapper;

import br.com.tecflix_app.modules.tag.application.domain.entity.Tag;
import br.com.tecflix_app.modules.tag.infra.presentation.dtos.v1.response.TagResponseDTO;
import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TagPresentationMapper {
  TagResponseDTO map(Tag tag);

  List<TagResponseDTO> map(List<Tag> tag);

  Set<TagResponseDTO> map(Set<Tag> tag);
}
