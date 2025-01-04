package br.com.tecflix_app.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.tecflix_app.controller.CourseController;
import br.com.tecflix_app.data.DTO.v1.create.CreateCourseDTO;
import br.com.tecflix_app.data.DTO.v1.response.CourseDTO;
import br.com.tecflix_app.data.DTO.v1.response.GenericResponseDTO;
import br.com.tecflix_app.data.DTO.v1.response.UserDTO;
import br.com.tecflix_app.exception.general.ResourceNotFoundException;
import br.com.tecflix_app.mapper.contract.IMapperService;
import br.com.tecflix_app.model.Course;
import br.com.tecflix_app.projection.CourseProjection;
import br.com.tecflix_app.repository.CourseRepository;
import br.com.tecflix_app.service.auth.jwt.TokenService;
import br.com.tecflix_app.service.util.CourseValidatorService;

@Service
public class CourseService {
    private final Logger LOGGER = Logger.getLogger(CourseService.class.getName());

    private final CourseRepository repository;
    private final CourseValidatorService courseValidatorService;
    private final TokenService tokenService;
    private final UserService userService;
    private final IMapperService mapper;
    private final PagedResourcesAssembler<CourseDTO> assembler;

    public CourseService(
        CourseRepository repository,
        CourseValidatorService courseValidatorService,
        TokenService tokenService,
        UserService userService,
        IMapperService mapper,
        PagedResourcesAssembler<CourseDTO> assembler
    ) {
        this.repository = repository;
        this.courseValidatorService = courseValidatorService;
        this.tokenService = tokenService;
        this.userService = userService;
        this.mapper = mapper;
        this.assembler = assembler;
    }

    public CourseDTO findById(UUID id) {
        LOGGER.info("Finding course by id");
        CourseDTO courseDTO = mapper.map(
            repository.findDetailsById(id).orElseThrow(
                () -> new ResourceNotFoundException("Nenhum curso encontrado para este id")
            ),
    CourseDTO.class
        );
        return addLiks(courseDTO, "courses");
    }

    public PagedModel<EntityModel<CourseDTO>> findByFilter(Long[] tagIds, String term, Pageable pageable) {
        if (tagIds != null && term != null) return findByTagIdsAndTerm(tagIds, term, pageable); 
        if (tagIds != null) return findByTagIds(tagIds, pageable);     
        return findByTerm(term, pageable);      
    }

    public PagedModel<EntityModel<CourseDTO>> findAll(Pageable pageable) {
        LOGGER.info("Finding all courses");
        
        Page<CourseProjection> entities = repository.findAllBy(pageable);
        Page<CourseDTO> courses = entities.map(course -> mapper.map(course, CourseDTO.class));


        courses = addLiks(courses, "courses");
        return assembler.toModel(
            courses,
            linkTo(
                methodOn(CourseController.class)
                .findAll(
                    pageable.getPageNumber(),
                    pageable.getPageSize(),
                    pageable.getSort().toString()
                )).withSelfRel()
        );
    }
    
    public PagedModel<EntityModel<CourseDTO>> findByTagIds(Long[] tagIds, Pageable pageable) {
        LOGGER.info("Finding courses by tag ids");
        
        Page<CourseProjection> entities = repository.findByTagIds(tagIds, pageable);
        Page<CourseDTO> courses = entities.map(course -> mapper.map(course, CourseDTO.class));

        courses = addLiks(courses, "courses");
        return assembler.toModel(
            courses,
            linkTo(
                methodOn(CourseController.class)
                .findAll(
                    pageable.getPageNumber(),
                    pageable.getPageSize(),
                    pageable.getSort().toString()
                )).withSelfRel()
        );
    }

    public PagedModel<EntityModel<CourseDTO>> findByTerm(String term, Pageable pageable) {
        LOGGER.info("Finding courses by term");
        
        Page<CourseProjection> entities = repository.findByTerm(term, pageable);
        Page<CourseDTO> courses = entities.map(course -> mapper.map(course, CourseDTO.class));

        courses = addLiks(courses, "courses");
        return assembler.toModel(
            courses,
            linkTo(
                methodOn(CourseController.class)
                .findAll(
                    pageable.getPageNumber(),
                    pageable.getPageSize(),
                    pageable.getSort().toString()
                )).withSelfRel()
        );
    }

    public PagedModel<EntityModel<CourseDTO>> findByTagIdsAndTerm(Long[] tagIds, String term, Pageable pageable) {
        LOGGER.info("Finding courses by tag ids and term");
        
        Page<CourseProjection> entities = repository.findByTagIdsAndTerm(tagIds, term, pageable);
        Page<CourseDTO> courses = entities.map(course -> mapper.map(course, CourseDTO.class));

        courses = addLiks(courses, "courses");
        return assembler.toModel(
            courses,
            linkTo(
                methodOn(CourseController.class)
                .findAll(
                    pageable.getPageNumber(),
                    pageable.getPageSize(),
                    pageable.getSort().toString()
                )).withSelfRel()
        );
    }

    @Transactional(rollbackFor = Exception.class)
    public GenericResponseDTO<UUID> create(CreateCourseDTO data) {
        LOGGER.info("Creating a new course");
        
        courseValidatorService.validateTags(data.getTags());
        
        data.setTitle(data.getTitle().trim());
        data.setDescription(data.getDescription().trim());
        
        UserDTO professor = userService.findById(tokenService.getUserId());
        data.setProfessor(professor);

        Course entity = mapper.map(data, Course.class);

        UUID id = repository.save(entity).getId();

        return new GenericResponseDTO<>(
            id,
            "Curso criado com sucesso",
            LocalDateTime.now()
        );
    }

    private CourseDTO addLiks(CourseDTO data, String rel) {
        data.add(linkTo(methodOn(CourseController.class).findById(data.getId())).withSelfRel());
        data.add(linkTo(methodOn(CourseController.class).findAll(0, 0, "ASC")).withRel(rel));
        return data;
    }
    
    private Page<CourseDTO> addLiks(Page<CourseDTO> data, String rel) {
        for (CourseDTO courseDTO : data) {
            courseDTO = addLiks(courseDTO, rel);
        }
        return data;
    }
}
