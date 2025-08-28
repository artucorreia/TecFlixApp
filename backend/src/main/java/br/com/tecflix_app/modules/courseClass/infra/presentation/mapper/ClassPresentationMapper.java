package br.com.tecflix_app.modules.courseClass.infra.presentation.mapper;

import br.com.tecflix_app.modules.courseClass.application.domain.entity.Class;
import br.com.tecflix_app.modules.courseClass.infra.presentation.dtos.v1.ClassDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClassPresentationMapper {
  ClassDTO domainToResponseDTO(Class courseClass);

  List<ClassDTO> domainToResponseDTO(List<Class> classes);
}
