package br.com.tecflix_app.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.tecflix_app.modules.courseClass.infra.presentation.ClassController;
import br.com.tecflix_app.modules.courseClass.infra.dtos.v1.CreateClassDTO;
import br.com.tecflix_app.modules.courseClass.infra.dtos.v1.ClassDTO;
import br.com.tecflix_app.shared.dto.v1.GenericResponseDTO;
import br.com.tecflix_app.shared.exception.general.ResourceNotFoundException;
import br.com.tecflix_app.mapper.contract.IMapperService;
import br.com.tecflix_app.modules.courseClass.infra.persistence.ClassRepository;
import br.com.tecflix_app.service.util.ClassValidatorService;
import br.com.tecflix_app.modules.courseClass.infra.persistence.ClassEntity;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ClassService {
  private final Logger LOGGER = Logger.getLogger(ClassService.class.getName());

  private final ClassRepository repository;
  private final ClassValidatorService classValidatorService;
  private final IMapperService mapper;

  public ClassService(
      ClassRepository repository,
      ClassValidatorService classValidatorService,
      IMapperService mapper) {
    this.repository = repository;
    this.classValidatorService = classValidatorService;
    this.mapper = mapper;
  }

  public ClassDTO findById(UUID id) {
    LOGGER.info("Finding class by id");
    ClassDTO classDTO =
        mapper.map(
            repository
                .findById(id)
                .orElseThrow(
                    () -> new ResourceNotFoundException("Nenhuma aula encontrada para este id")),
            ClassDTO.class);
    return addLiks(classDTO, "classes");
  }

  public List<ClassDTO> findAll() {
    LOGGER.info("Finding all classes");
    List<ClassDTO> classes = mapper.map(repository.findAll(), ClassDTO.class);
    return addLiks(classes, "classes");
  }

  @Transactional(rollbackFor = Exception.class)
  public GenericResponseDTO<UUID> create(CreateClassDTO data) {
    LOGGER.info("Creating a new class to module: " + data.getModule().getId());
    classValidatorService.validateCourse(data.getModule().getId());
    data.setTitle(data.getTitle().trim());

    ClassEntity entity = mapper.map(data, ClassEntity.class);
    UUID id = repository.save(entity).getId();

    return new GenericResponseDTO<>(id, "Aula criada com sucesso", LocalDateTime.now());
  }

  private ClassDTO addLiks(ClassDTO data, String rel) {
    data.add(linkTo(methodOn(ClassController.class).findById(data.getId())).withSelfRel());
    data.add(linkTo(methodOn(ClassController.class).findAll()).withRel(rel));
    return data;
  }

  private List<ClassDTO> addLiks(List<ClassDTO> data, String rel) {
    return data.stream().map(obj -> obj = addLiks(obj, rel)).toList();
  }
}
