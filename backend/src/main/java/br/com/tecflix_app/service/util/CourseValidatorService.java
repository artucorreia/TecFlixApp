package br.com.tecflix_app.service.util;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tecflix_app.data.DTO.v1.response.TagDTO;
import br.com.tecflix_app.service.TagService;

@Service
public class CourseValidatorService {
    private final Logger LOGGER = Logger.getLogger(CourseValidatorService.class.getName());

    private final TagService tagService;

    @Autowired
    public CourseValidatorService(
        TagService tagService
    ) {
        this.tagService = tagService;
    }

    public void validateTags(List<TagDTO> tags) {
        LOGGER.info("Validating tags");
        for (TagDTO tag : tags) { tagService.findById(tag.getId()); }
    }
}
