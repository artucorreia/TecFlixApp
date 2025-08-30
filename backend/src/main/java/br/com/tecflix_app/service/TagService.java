package br.com.tecflix_app.service;

import java.util.List;
import java.util.logging.Logger;

import br.com.tecflix_app.modules.tag.infra.presentation.dtos.v1.response.TagResponseDTO;
import org.springframework.stereotype.Service;

import br.com.tecflix_app.modules.shared.exception.general.ResourceNotFoundException;
import br.com.tecflix_app.mapper.contract.IMapperService;
import br.com.tecflix_app.modules.tag.infra.persistence.TagRepository;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Service
public class TagService {
  private final Logger LOGGER = Logger.getLogger(TagService.class.getName());

  private final TagRepository repository;
  private final IMapperService mapper;

  public TagService(TagRepository repository, IMapperService mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  public TagResponseDTO findById(Long id) {
    LOGGER.info("Finding tag by id");
    TagResponseDTO tag =
        mapper.map(
            repository
                .findById(id)
                .orElseThrow(
                    () -> new ResourceNotFoundException("Nenhuma tag encontrada para este id")),
            TagResponseDTO.class);
    return tag;
  }

  public List<TagResponseDTO> findAll() {
    LOGGER.info("Finding all tags");
    return mapper.map(repository.findAll(), TagResponseDTO.class);
  }

//  private TagDTO addLiks(TagDTO data, String rel) {
//    data.add(linkTo(methodOn(TagController.class).findById(data.getId())).withSelfRel());
//    data.add(linkTo(methodOn(TagController.class).findAll()).withRel(rel));
//    return data;
//  }
//
//  private List<TagDTO> addLiks(List<TagDTO> data, String rel) {
//    return data.stream().map(obj -> obj = addLiks(obj, rel)).toList();
//  }
}
