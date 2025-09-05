package br.com.tecflix_app.modules.courseClass.infra.presentation.mapper;

import br.com.tecflix_app.modules.courseClass.application.domain.entity.Class;
import br.com.tecflix_app.modules.courseClass.infra.presentation.dtos.v1.ClassDTO;
import br.com.tecflix_app.modules.courseClass.infra.presentation.dtos.v1.CreateClassDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClassPresentationMapper {
  ClassDTO domainToResponseDTO(Class courseClass);

  List<ClassDTO> domainToResponseDTO(List<Class> classes);

  @Mappings(
      value = {
          @Mapping(target = "id", ignore = true),
          @Mapping(target = "active", ignore = true),
          @Mapping(target = "createdAt", ignore = true),
          @Mapping(target = "module.id", source = "moduleId")
      })
  Class createDTOToDomain(CreateClassDTO createClassDTO);
}
