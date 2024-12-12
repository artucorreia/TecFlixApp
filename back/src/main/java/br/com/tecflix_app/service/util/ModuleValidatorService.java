package br.com.tecflix_app.service.util;

import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tecflix_app.service.CourseService;

@Service
public class ModuleValidatorService {
    private final Logger LOGGER = Logger.getLogger(ModuleValidatorService.class.getName());

    private final CourseService courseService;

    @Autowired
    public ModuleValidatorService(CourseService courseService) { this.courseService = courseService; }

    public void validateCourse(UUID courseId) {
        LOGGER.info("Validating course");
        courseService.findById(courseId);
    }
}
