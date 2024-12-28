package br.com.tecflix_app.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.tecflix_app.data.DTO.v1.create.CreateReviewDTO;
import br.com.tecflix_app.data.DTO.v1.response.GenericResponseDTO;
import br.com.tecflix_app.data.DTO.v1.response.ReviewDTO;
import br.com.tecflix_app.service.ReviewService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/reviews")
public class ReviewController {

    private final ReviewService service;
    
    @Autowired
    public ReviewController(ReviewService service) { this.service = service; }
    
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ReviewDTO>> findByCourseId(
        @RequestParam(name = "courseId", required = true) UUID courseId
    ) {
        return ResponseEntity.ok(service.findByCourseId(courseId));
    }

    @PostMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<GenericResponseDTO<Long>> create(@Valid @RequestBody CreateReviewDTO data) {
        return ResponseEntity.ok(service.create(data));
    }
}
