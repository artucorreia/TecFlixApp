package br.com.tecflix_app.service.util;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tecflix_app.modules.tag.infra.dtos.v1.response.TagResponse;
import br.com.tecflix_app.service.TagService;

@Service
public class CourseValidatorService {
  private final Logger LOGGER = Logger.getLogger(CourseValidatorService.class.getName());

  private final TagService tagService;

  @Autowired
  public CourseValidatorService(TagService tagService) {
    this.tagService = tagService;
  }

  public void validateTags(List<TagResponse> tags) {
    LOGGER.info("Validating tags");
    for (TagResponse tag : tags) {
      tagService.findById(tag.getId());
    }
  }
}
