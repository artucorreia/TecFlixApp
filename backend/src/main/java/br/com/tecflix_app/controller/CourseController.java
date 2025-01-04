package br.com.tecflix_app.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
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

import br.com.tecflix_app.data.DTO.v1.create.CreateCourseDTO;
import br.com.tecflix_app.data.DTO.v1.create.CreateReviewDTO;
import br.com.tecflix_app.data.DTO.v1.response.CourseDTO;
import br.com.tecflix_app.data.DTO.v1.response.GenericResponseDTO;
import br.com.tecflix_app.data.DTO.v1.response.ReviewDTO;
import br.com.tecflix_app.service.CourseService;
import br.com.tecflix_app.service.ReviewService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/courses")
public class CourseController {

    private final CourseService service;
    private final ReviewService reviewService;

    @Autowired
    public CourseController(
        CourseService service,
        ReviewService reviewService
    ) {
        this.service = service;
        this.reviewService = reviewService;
    }

    @GetMapping(
        value = "/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE 
    )
    public ResponseEntity<CourseDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PagedModel<EntityModel<CourseDTO>>> findAll(
        @RequestParam(name = "page", defaultValue = "0") Integer page,
        @RequestParam(name = "size", defaultValue = "10") Integer size,
        @RequestParam(name = "direction", defaultValue = "asc") String direction
    ) {
        Direction sortDirection = 
            "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "totalScoreReviews"));
        
        return ResponseEntity.ok(service.findAll(pageable));
    }
    
    @GetMapping(
        value = "/search",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<PagedModel<EntityModel<CourseDTO>>> search(
        @RequestParam(name = "tags", required = false) Long[] tags,
        @RequestParam(name = "term", required = false) String term,
        @RequestParam(name = "page", defaultValue = "0") Integer page,
        @RequestParam(name = "size", defaultValue = "10") Integer size,
        @RequestParam(name = "direction", defaultValue = "asc") String direction
    ) {
        Direction sortDirection = 
            "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "totalScoreReviews"));
        
        if (tags != null || term != null) return ResponseEntity.ok(service.findByFilter(tags, term, pageable));  
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @PostMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<GenericResponseDTO<UUID>> create(@Valid @RequestBody CreateCourseDTO data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(data));
    }


    /*
    * Reviews
    */

    @GetMapping(
        value = "/{id}/reviews",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<ReviewDTO>> findByCourseId(@PathVariable UUID id) {
        return ResponseEntity.ok(reviewService.findByCourseId(id));
    }

    @PostMapping(
        value = "/{id}/reviews",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<GenericResponseDTO<Long>> create(@PathVariable UUID id, @Valid @RequestBody CreateReviewDTO data) {
        return ResponseEntity.ok(reviewService.create(id, data));
    }
}
