package br.com.tecflix_app.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import br.com.tecflix_app.controller.TagController;
import br.com.tecflix_app.data.DTO.v1.response.TagDTO;
import br.com.tecflix_app.exception.general.ResourceNotFoundException;
import br.com.tecflix_app.mapper.contract.IMapperService;
import br.com.tecflix_app.repository.TagRepository;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Service
public class TagService {
    private final Logger LOGGER = Logger.getLogger(TagService.class.getName());

    private final TagRepository repository;
    private final IMapperService mapper;

    public TagService(
        TagRepository repository,
        IMapperService mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public TagDTO findById(Long id) {
        LOGGER.info("Finding tag by id");
        TagDTO tag = mapper.map(
            repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Nenhuma tag encontrada para este id")
            ),
    TagDTO.class
        );
        return addLiks(tag, "tags");
    }
    
    public List<TagDTO> findByAll() {
        LOGGER.info("Finding all tags");
        List<TagDTO> tags =  mapper.map(repository.findAll(), TagDTO.class);
        return addLiks(tags, "tags");
    }

    private TagDTO addLiks(TagDTO data, String rel) {
        data.add(linkTo(methodOn(TagController.class).findById(data.getId())).withSelfRel());
        data.add(linkTo(methodOn(TagController.class).findAll()).withRel(rel));
        return data;
    }
    
    private List<TagDTO> addLiks(List<TagDTO> data, String rel) {
        return data.stream().map(
            obj -> obj = addLiks(obj, rel)
        ).toList();
    }
}
