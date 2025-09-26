package br.com.tecflix_app.modules.tag.infra.gateways.mapper;

import br.com.tecflix_app.modules.course.infra.persistence.projections.CourseDetailsProjection;
import br.com.tecflix_app.modules.tag.application.domain.entity.Tag;
import br.com.tecflix_app.modules.tag.infra.persistence.TagEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TagGatewayMapper {

  @Named("tagCourseDetailsProjectionToTag")
  @Mappings(
      value = {
        @Mapping(target = "createdAt", ignore = true),
      })
  Tag courseDetailsProjectionToDomain(
      CourseDetailsProjection.TagCourseDetailsProjection tagCourseDetailsProjection);

  TagEntity domainToEntity(Tag tag);

  @Mappings(value = {@Mapping(target = "courses", ignore = true)})
  List<Tag> entityToDomain(List<TagEntity> tagEntities);

  Tag map(TagEntity tagEntity);
}
