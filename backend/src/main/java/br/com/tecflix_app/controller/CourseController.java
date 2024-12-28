package br.com.tecflix_app.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.tecflix_app.controller.contract.IController;
import br.com.tecflix_app.data.DTO.v1.create.CreateCourseDTO;
import br.com.tecflix_app.data.DTO.v1.response.CourseDTO;
import br.com.tecflix_app.data.DTO.v1.response.GenericResponseDTO;
import br.com.tecflix_app.service.CourseService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/courses")
public class CourseController implements IController<CourseDTO, UUID>{

    private final CourseService service;
    
    @Autowired
    public CourseController(CourseService service) { this.service = service; }

    @GetMapping(
        value = "/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE 
    )
    public ResponseEntity<CourseDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CourseDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
    
    @GetMapping(
        value = "/search",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<CourseDTO>> search(
        @RequestParam(name = "tags", required = false) Long[] tags,
        @RequestParam(name = "term", required = false) String term
    ) {
        if (tags != null || term != null) return ResponseEntity.ok(service.findByFilter(tags, term));  
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<GenericResponseDTO<UUID>> create(@Valid @RequestBody CreateCourseDTO data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(data));
    }
}
