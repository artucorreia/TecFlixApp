package br.com.tecflix_app.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

import br.com.tecflix_app.modules.module.infra.presentation.dtos.v1.ModuleResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.tecflix_app.modules.module.infra.persistence.ModuleEntity;
import br.com.tecflix_app.modules.module.infra.presentation.ModuleController;
import br.com.tecflix_app.modules.module.infra.presentation.dtos.v1.CreateModuleDTO;
import br.com.tecflix_app.modules.shared.dto.v1.GenericResponseDTO;
import br.com.tecflix_app.modules.shared.exception.general.ResourceNotFoundException;
import br.com.tecflix_app.mapper.contract.IMapperService;
import br.com.tecflix_app.modules.module.infra.persistence.ModuleRepository;
import br.com.tecflix_app.service.util.ModuleValidatorService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ModuleService {
  private final Logger LOGGER = Logger.getLogger(ModuleService.class.getName());

  private final ModuleRepository repository;
  private final ModuleValidatorService moduleValidatorService;
  private final IMapperService mapper;

  public ModuleService(
      ModuleRepository repository,
      ModuleValidatorService moduleValidatorService,
      IMapperService mapper) {
    this.repository = repository;
    this.moduleValidatorService = moduleValidatorService;
    this.mapper = mapper;
  }

  public ModuleResponseDTO findById(Long id) {
    LOGGER.info("Finding module by id");
    return mapper.map(
        repository
            .findById(id)
            .orElseThrow(
                () -> new ResourceNotFoundException("Nenhum módulo encontrado para este id")),
        ModuleResponseDTO.class);
  }
}
