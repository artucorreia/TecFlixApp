package br.com.tecflix_app.modules.course.infra.gateways.mapper;

import br.com.tecflix_app.modules.course.application.domain.entity.Course;
import br.com.tecflix_app.modules.course.infra.persistence.CourseEntity;
import br.com.tecflix_app.modules.course.infra.persistence.projections.CourseDetailsProjection;
import br.com.tecflix_app.modules.module.infra.gateways.mapper.ModuleGatewaysMapper;
import br.com.tecflix_app.modules.tag.infra.gateways.mapper.TagGatewayMapper;
import br.com.tecflix_app.modules.user.infra.gateways.mapper.UserGatewaysMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(
    componentModel = "spring",
    uses = {UserGatewaysMapper.class, TagGatewayMapper.class, ModuleGatewaysMapper.class})
public interface CourseGatewaysMapper {
  @Mappings(
      value = {
        @Mapping(target = "students", ignore = true),
        @Mapping(
            target = "modules",
            source = "modules",
            qualifiedByName = "moduleDetailsProjectionToModule"),
        @Mapping(
            target = "tags",
            source = "tags",
            qualifiedByName = "tagCourseDetailsProjectionToTag"),
        @Mapping(
            target = "professor",
            source = "professor",
            qualifiedByName = "userBasicProjectionToUser")
      })
  Course detailsProjectionToDomain(CourseDetailsProjection courseDetailsProjection);

  @Mappings(
      value = {
        @Mapping(target = "students", ignore = true),
        @Mapping(
            target = "professor",
            source = "professor",
            qualifiedByName = "entityToDomainWithoutCourses")
      })
  Course entityToDomain(CourseEntity courseEntity);

  CourseEntity entityToDomain(Course course);
}
