package br.com.tecflix_app.modules.module.infra.presentation.mapper;

import br.com.tecflix_app.modules.module.application.domain.entity.Module;
import br.com.tecflix_app.modules.module.infra.presentation.dtos.v1.ModuleResponseDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ModulePresentationMapper {
  ModuleResponseDTO domainToResponseDTO(Module module);

  List<ModuleResponseDTO> domainToResponseDTO(List<Module> modules);
}
