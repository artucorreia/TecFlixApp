package br.com.tecflix_app.modules.course.infra.presentation.mapper;

import br.com.tecflix_app.modules.course.application.domain.entity.Course;
import br.com.tecflix_app.modules.course.infra.presentation.dtos.v1.CourseResponseDTO;
import br.com.tecflix_app.modules.course.infra.presentation.dtos.v1.CreateCourseDTO;
import br.com.tecflix_app.modules.module.infra.presentation.mapper.ModulePresentationMapper;
import br.com.tecflix_app.modules.tag.infra.presentation.mapper.TagPresentationMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(
    componentModel = "spring",
    uses = {ModulePresentationMapper.class, TagPresentationMapper.class})
public interface CoursePresentationMapper {

  CourseResponseDTO domainToResponseDTO(Course course);

  @Mappings(
      value = {
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "active", ignore = true),
        @Mapping(target = "createdAt", ignore = true),
        @Mapping(target = "totalScore", ignore = true),
        @Mapping(target = "totalReviews", ignore = true),
        @Mapping(target = "averageScore", ignore = true),
        @Mapping(target = "professor", ignore = true),
        @Mapping(target = "students", ignore = true),
        @Mapping(target = "modules", ignore = true),
      })
  Course createDTOToDomain(CreateCourseDTO createCourseDTO);
}
