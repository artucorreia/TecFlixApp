package br.com.tecflix_app.modules.course.infra.presentation.mapper;

import br.com.tecflix_app.modules.course.application.domain.entity.Course;
import br.com.tecflix_app.modules.course.infra.presentation.dtos.v1.CourseResponseDTO;
import br.com.tecflix_app.modules.course.infra.presentation.dtos.v1.CourseUserProfileResponseDTO;
import br.com.tecflix_app.modules.course.infra.presentation.dtos.v1.CreateCourseDTO;
import br.com.tecflix_app.modules.tag.application.domain.entity.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CoursePresentationMapper {

  CourseResponseDTO map(Course course);

  @Mappings(
      value = {
        @Mapping(target = "createdAt", ignore = true),
        @Mapping(target = "createdBy", ignore = true),
        @Mapping(target = "updatedAt", ignore = true),
        @Mapping(target = "updatedBy", ignore = true),
        @Mapping(target = "deleted", ignore = true),
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "totalScore", ignore = true),
        @Mapping(target = "totalReviews", ignore = true),
        @Mapping(target = "averageScore", ignore = true),
        @Mapping(target = "professor", ignore = true),
        @Mapping(target = "students", ignore = true),
        @Mapping(target = "tags", source = "tagIds")
      })
  Course map(CreateCourseDTO createCourseDTO);

  default Tag map(Long id) {
    if (id == null) return null;
    Tag tag = new Tag();
    tag.setId(id);
    return tag;
  }

  @Mappings(
      value = {
        @Mapping(target = "professorId", source = "professor.id"),
        @Mapping(target = "professorName", source = "professor.name"),
      })
  CourseUserProfileResponseDTO mapCourseUserProfileResponseDTO(Course course);

  List<CourseUserProfileResponseDTO> map(List<Course> courses);

  List<CourseResponseDTO> domainToCourseResponseDTO(List<Course> courses);
}
