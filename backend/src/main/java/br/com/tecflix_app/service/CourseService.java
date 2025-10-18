package br.com.tecflix_app.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.tecflix_app.modules.course.infra.presentation.CourseController;
import br.com.tecflix_app.modules.course.infra.presentation.dtos.v1.CourseResponseDTO;
import br.com.tecflix_app.modules.shared.dto.v1.CustomPagedResponse;
import br.com.tecflix_app.modules.shared.exception.general.ResourceNotFoundException;
import br.com.tecflix_app.mapper.contract.IMapperService;
import br.com.tecflix_app.modules.course.infra.persistence.projections.CourseProjection;
import br.com.tecflix_app.modules.course.infra.persistence.CourseRepository;
import br.com.tecflix_app.modules.auth.infra.security.jwt.TokenService;
import br.com.tecflix_app.service.util.CourseValidatorService;
import br.com.tecflix_app.service.util.CustomPagedResourcesAssembler;

@Service
public class CourseService {
  private final Logger LOGGER = Logger.getLogger(CourseService.class.getName());

  private final CourseRepository repository;
  private final CourseValidatorService courseValidatorService;
  private final TokenService tokenService;
  private final IMapperService mapper;
  private final CustomPagedResourcesAssembler<CourseResponseDTO> assembler;

  public CourseService(
      CourseRepository repository,
      CourseValidatorService courseValidatorService,
      TokenService tokenService,
      IMapperService mapper,
      CustomPagedResourcesAssembler<CourseResponseDTO> assembler) {
    this.repository = repository;
    this.courseValidatorService = courseValidatorService;
    this.tokenService = tokenService;
    this.mapper = mapper;
    this.assembler = assembler;
  }

  public CourseResponseDTO findById(UUID id) {
    LOGGER.info("Finding course by id");
    CourseResponseDTO courseResponseDTO =
        mapper.map(
            repository
                .findDetailsById(id)
                .orElseThrow(
                    () -> new ResourceNotFoundException("Nenhum curso encontrado para este id")),
            CourseResponseDTO.class);
    return addLiks(courseResponseDTO, "courses");
  }

  public CustomPagedResponse<CourseResponseDTO> findAll(Pageable pageable) {
    LOGGER.info("Finding all courses");

    Page<CourseProjection> entities = repository.findAllByDeletedFalseAndApprovedTrue(pageable);
    Page<CourseResponseDTO> courses = entities.map(course -> mapper.map(course, CourseResponseDTO.class));

    courses = addLiks(courses, "courses");

    return assembler.toModel(courses, addPageLinks(courses, pageable), pageable);
  }

  public CustomPagedResponse<CourseResponseDTO> findByFilter(
      Long[] tagIds, String term, Pageable pageable) {
    if (tagIds != null && term != null) return findByTagIdsAndTerm(tagIds, term, pageable);
    if (tagIds != null) return findByTagIds(tagIds, pageable);
    return findByTerm(term, pageable);
  }

  public CustomPagedResponse<CourseResponseDTO> findByTagIds(Long[] tagIds, Pageable pageable) {
    LOGGER.info("Finding courses by tag ids");

    Page<CourseProjection> entities = repository.findByTagIds(tagIds, pageable);
    Page<CourseResponseDTO> courses = entities.map(course -> mapper.map(course, CourseResponseDTO.class));

    courses = addLiks(courses, "courses");
    return assembler.toModel(courses, addPageLinks(courses, pageable), pageable);
  }

  public CustomPagedResponse<CourseResponseDTO> findByTerm(String term, Pageable pageable) {
    LOGGER.info("Finding courses by term");

    Page<CourseProjection> entities = repository.findByTerm(term, pageable);
    Page<CourseResponseDTO> courses = entities.map(course -> mapper.map(course, CourseResponseDTO.class));

    courses = addLiks(courses, "courses");
    return assembler.toModel(courses, addPageLinks(courses, pageable), pageable);
  }

  public CustomPagedResponse<CourseResponseDTO> findByTagIdsAndTerm(
      Long[] tagIds, String term, Pageable pageable) {
    LOGGER.info("Finding courses by tag ids and term");

    Page<CourseProjection> entities = repository.findByTagIdsAndTerm(tagIds, term, pageable);
    Page<CourseResponseDTO> courses = entities.map(course -> mapper.map(course, CourseResponseDTO.class));

    courses = addLiks(courses, "courses");
    return assembler.toModel(courses, addPageLinks(courses, pageable), pageable);
  }

//  @Transactional(rollbackFor = Exception.class)
//  public GenericResponseDTO<UUID> create(CreateCourseDTO data) {
//    LOGGER.info("Creating a new course");
//
//    courseValidatorService.validateTags(data.getTags());
//
//    data.setTitle(data.getTitle().trim());
//    data.setDescription(data.getDescription().trim());
//
//    UserDTO professor = new UserDTO();
//    professor.setId(tokenService.getUserId());
//    data.setProfessor(professor);
//
//    CourseEntity entity = mapper.map(data, CourseEntity.class);
//
//    UUID id = repository.save(entity).getId();
//
//    return new GenericResponseDTO<>(id, "Curso criado com sucesso", LocalDateTime.now());
//  }

  private CourseResponseDTO addLiks(CourseResponseDTO data, String rel) {
    data.add(linkTo(methodOn(CourseController.class).findById(data.getId())).withSelfRel());
    data.add(
        linkTo(methodOn(CourseController.class).findAll(0, 0, "totalScoreReviews,ASC"))
            .withRel(rel));
    return data;
  }

  private Page<CourseResponseDTO> addLiks(Page<CourseResponseDTO> data, String rel) {
    for (CourseResponseDTO courseResponseDTO : data) {
      courseResponseDTO = addLiks(courseResponseDTO, rel);
    }
    return data;
  }

  private Map<String, String> addPageLinks(Page<CourseResponseDTO> page, Pageable pageable) {
    Map<String, String> links = new LinkedHashMap<>();
    links.put(
        "self",
        linkTo(
                methodOn(CourseController.class)
                    .findAll(
                        pageable.getPageNumber(),
                        pageable.getPageSize(),
                        pageable.getSort().toString()))
            .withSelfRel()
            .getHref());
    links.put(
        "firts",
        linkTo(
                methodOn(CourseController.class)
                    .findAll(0, pageable.getPageSize(), pageable.getSort().toString()))
            .withRel("first")
            .getHref());
    links.put(
        "last",
        linkTo(
                methodOn(CourseController.class)
                    .findAll(
                        page.getTotalPages() - 1,
                        pageable.getPageSize(),
                        pageable.getSort().toString()))
            .withRel("last")
            .getHref());
    links.put(
        "next",
        page.hasNext()
            ? linkTo(
                    methodOn(CourseController.class)
                        .findAll(
                            pageable.getPageNumber() + 1,
                            pageable.getPageSize(),
                            pageable.getSort().toString()))
                .withRel("next")
                .getHref()
            : null);
    links.put(
        "prev",
        page.hasPrevious()
            ? linkTo(
                    methodOn(CourseController.class)
                        .findAll(
                            pageable.getPageNumber() - 1,
                            pageable.getPageSize(),
                            pageable.getSort().toString()))
                .withRel("prev")
                .getHref()
            : null);
    return links;
  }
}
