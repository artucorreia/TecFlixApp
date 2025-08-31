package br.com.tecflix_app.modules.module.infra.presentation.mapper;

import br.com.tecflix_app.modules.module.application.domain.entity.Module;
import br.com.tecflix_app.modules.module.infra.presentation.dtos.v1.CreateModuleDTO;
import br.com.tecflix_app.modules.module.infra.presentation.dtos.v1.ModuleResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ModulePresentationMapper {
  ModuleResponseDTO domainToResponseDTO(Module module);

  List<ModuleResponseDTO> domainToResponseDTO(List<Module> modules);

  @Mappings(
      value = {
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "active", ignore = true),
        @Mapping(target = "createdAt", ignore = true),
        @Mapping(target = "course.id", source = "courseId")
      })
  Module createDTOToDomain(CreateModuleDTO createModuleDTO);
}
