package br.com.tecflix_app.modules.courseClass.infra.presentation.mapper;

import br.com.tecflix_app.modules.courseClass.application.domain.entity.Class;
import br.com.tecflix_app.modules.courseClass.infra.presentation.dtos.v1.ClassDTO;
import br.com.tecflix_app.modules.courseClass.infra.presentation.dtos.v1.CreateClassDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface ClassPresentationMapper {
  ClassDTO map(Class courseClass);

  List<ClassDTO> map(List<Class> classes);

  @Mappings(
      value = {
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "createdAt", ignore = true),
        @Mapping(target = "createdBy", ignore = true),
        @Mapping(target = "updatedAt", ignore = true),
        @Mapping(target = "updatedBy", ignore = true),
        @Mapping(target = "deleted", ignore = true),
        @Mapping(target = "module.id", source = "moduleId")
      })
  Class map(CreateClassDTO createClassDTO);

  Set<ClassDTO> map(Set<Class> courseClasses);
}
