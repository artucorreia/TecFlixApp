package br.com.tecflix_app.service.util;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tecflix_app.service.ModuleService;

@Service
public class ClassValidatorService {
    private final Logger LOGGER = Logger.getLogger(ClassValidatorService.class.getName());

   private final ModuleService moduleService;

    @Autowired
    public ClassValidatorService(ModuleService moduleService) { this.moduleService = moduleService; }

    public void validateCourse(Long moduleId) {
        LOGGER.info("Validating module");
        moduleService.findById(moduleId);
    }
}
