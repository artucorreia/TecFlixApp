package br.com.tecflix_app.modules.module.infra.presentation.mapper;

import br.com.tecflix_app.modules.module.application.domain.entity.Module;
import br.com.tecflix_app.modules.module.infra.dtos.v1.ModuleDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ModulePresentationMapper {
  ModuleDTO domainToResponseDTO(Module module);

  List<ModuleDTO> domainToResponseDTO(List<Module> modules);
}
