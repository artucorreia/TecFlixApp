package br.com.tecflix_app.modules.course.infra.gateways.mapper;

import br.com.tecflix_app.modules.course.application.domain.entity.Course;
import br.com.tecflix_app.modules.course.infra.persistence.CourseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CourseGatewaysMapper {
  Course entityToDomain(CourseEntity courseEntity);

  @Mappings(value = {
      @Mapping(target = "tagEntities", source = "tags")
  })
  CourseEntity entityToDomain(Course course);
}
