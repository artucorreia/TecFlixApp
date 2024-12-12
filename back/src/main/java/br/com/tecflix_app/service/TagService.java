package br.com.tecflix_app.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import br.com.tecflix_app.data.DTO.v1.response.TagDTO;
import br.com.tecflix_app.exception.general.ResourceNotFoundException;
import br.com.tecflix_app.mapper.contract.IMapperService;
import br.com.tecflix_app.repository.TagRepository;

@Service
public class TagService {
    private final Logger LOGGER = Logger.getLogger(TagService.class.getName());

    private final TagRepository repository;
    // private final UserService userService;
    // private final TokenService tokenService;
    private final IMapperService mapper;

    public TagService(
        TagRepository repository,
        // UserService userService,
        // TokenService tokenService,
        IMapperService mapper
    ) {
        this.repository = repository;
        // this.userService = userService;
        // this.tokenService = tokenService;
        this.mapper = mapper;
    }

    public TagDTO findById(Long id) {
        LOGGER.info("Finding tag by id");
        return mapper.map(
            repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Nenhuma tag encontrada para este id")
            ),
    TagDTO.class
        );
    }
    
    public List<TagDTO> findByAll() {
        LOGGER.info("Finding all tags");
        return mapper.map(repository.findAll(), TagDTO.class);
    }

    // public List<CourseDTO> findCourses(Long id) {
    //     LOGGER.info("Finding courses");
    //     repository.findById(id);
    // }

}
