package br.com.tecflix_app.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.tecflix_app.data.DTO.v1.create.CreateCourseDTO;
import br.com.tecflix_app.data.DTO.v1.response.CourseDTO;
import br.com.tecflix_app.data.DTO.v1.response.GenericResponseDTO;
import br.com.tecflix_app.data.DTO.v1.response.UserDTO;
import br.com.tecflix_app.exception.general.ResourceNotFoundException;
import br.com.tecflix_app.mapper.contract.IMapperService;
import br.com.tecflix_app.model.Course;
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

    public CourseService(
        CourseRepository repository,
        CourseValidatorService courseValidatorService,
        TokenService tokenService,
        UserService userService,
        IMapperService mapper
    ) {
        this.repository = repository;
        this.courseValidatorService = courseValidatorService;
        this.tokenService = tokenService;
        this.userService = userService;
        this.mapper = mapper;
    }

    public CourseDTO findById(UUID id) {
        LOGGER.info("Finding course by id");
        return mapper.map(
            repository.findDetailsById(id).orElseThrow(
                () -> new ResourceNotFoundException("Nenhum curso encontrado para este id")
            ),
    CourseDTO.class
        );
    }

    public Set<CourseDTO> findByFilter(Long[] tagIds, String term) {
        if (tagIds != null && term != null) return findByTagIdsAndTerm(tagIds, term); 
        if (tagIds != null) return findByTagIds(tagIds);     
        return findByTerm(term);      
    }

    // TODO: pagination (ordered by reviews)
    public List<CourseDTO> findAll() {
        LOGGER.info("Finding all courses");
        return mapper.map(repository.findAll(), CourseDTO.class);
    }
    
    // TODO: pagination (ordered by reviews)
    public Set<CourseDTO> findByTagIds(Long[] tagIds) {
        LOGGER.info("Finding courses by tag ids");
        return mapper.map(repository.findByTagIds(tagIds), CourseDTO.class);
    }

    // TODO: pagination (ordered by reviews)
    public Set<CourseDTO> findByTerm(String term) {
        LOGGER.info("Finding courses by term");
        return mapper.map(repository.findByTerm(term), CourseDTO.class);
    }

    // TODO: pagination (ordered by reviews)
    public Set<CourseDTO> findByTagIdsAndTerm(Long[] tagIds, String term) {
        LOGGER.info("Finding courses by tag ids and term");
        return mapper.map(repository.findByTagIdsAndTerm(tagIds, term), CourseDTO.class);
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
}
