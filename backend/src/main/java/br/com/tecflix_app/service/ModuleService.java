package br.com.tecflix_app.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.tecflix_app.model.Module;
import br.com.tecflix_app.controller.ModuleController;
import br.com.tecflix_app.data.DTO.v1.create.CreateModuleDTO;
import br.com.tecflix_app.data.DTO.v1.response.GenericResponseDTO;
import br.com.tecflix_app.data.DTO.v1.response.ModuleDTO;
import br.com.tecflix_app.exception.general.ResourceNotFoundException;
import br.com.tecflix_app.mapper.contract.IMapperService;
import br.com.tecflix_app.repository.ModuleRepository;
import br.com.tecflix_app.service.util.HateoasService;
import br.com.tecflix_app.service.util.ModuleValidatorService;

@Service
public class ModuleService {
    private final Logger LOGGER = Logger.getLogger(ModuleService.class.getName());

    private final ModuleRepository repository;
    private final ModuleValidatorService moduleValidatorService;
    private final IMapperService mapper;

    public ModuleService(
        ModuleRepository repository,
        ModuleValidatorService moduleValidatorService,
        IMapperService mapper
    ) {
        this.repository = repository;
        this.moduleValidatorService = moduleValidatorService;
        this.mapper = mapper;
    }

     public ModuleDTO findById(Long id) {
        LOGGER.info("Finding module by id");
        ModuleDTO module = mapper.map(
            repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Nenhum módulo encontrado para este id")
            ),
            ModuleDTO.class
        );

        return HateoasService.addLiks(module, ModuleController.class, ModuleDTO::getId, "modules");
    }
    
    public List<ModuleDTO> findByAll() {
        LOGGER.info("Finding all modules");
        List<ModuleDTO> modules = mapper.map(repository.findAll(), ModuleDTO.class);
        return HateoasService.addLiks(modules, ModuleController.class, ModuleDTO::getId, "modules");
    }

    @Transactional(rollbackFor = Exception.class)
    public GenericResponseDTO<Long> create(CreateModuleDTO data) {
        LOGGER.info("Creating a new module to course: " + data.getCourse().getId());
        
        moduleValidatorService.validateCourse(data.getCourse().getId());
        
        data.setTitle(data.getTitle().trim());

        Module entity = mapper.map(data, Module.class);
        Long id = repository.save(entity).getId();

        return new GenericResponseDTO<>(
            id,
            "Módulo criado com sucesso",
            LocalDateTime.now()
        );
    }
}
