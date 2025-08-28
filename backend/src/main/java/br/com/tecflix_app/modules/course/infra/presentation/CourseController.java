package br.com.tecflix_app.modules.course.infra.presentation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import br.com.tecflix_app.modules.course.application.domain.entity.Course;
import br.com.tecflix_app.modules.course.application.usecases.CreateCourseUseCase;
import br.com.tecflix_app.modules.course.constant.CourseConstant;
import br.com.tecflix_app.modules.course.infra.presentation.mapper.CoursePresentationMapper;
import br.com.tecflix_app.modules.shared.dto.v1.ResponseDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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

import br.com.tecflix_app.modules.course.infra.dtos.v1.CreateCourseDTO;
import br.com.tecflix_app.modules.review.infra.dtos.v1.CreateReviewDTO;
import br.com.tecflix_app.modules.course.infra.presentation.dtos.v1.CourseResponseDTO;
import br.com.tecflix_app.modules.shared.dto.v1.CustomPagedResponse;
import br.com.tecflix_app.modules.shared.dto.v1.GenericResponseDTO;
import br.com.tecflix_app.modules.review.infra.dtos.v1.ReviewDTO;
import br.com.tecflix_app.modules.course.infra.persistence.projections.CourseDetailsProjection;
import br.com.tecflix_app.modules.course.infra.persistence.projections.CourseProjection;
import br.com.tecflix_app.modules.review.infra.persistence.projections.ReviewProjection;
import br.com.tecflix_app.service.CourseService;
import br.com.tecflix_app.service.ReviewService;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/v1/courses")
@SecurityRequirements(
    value = {@SecurityRequirement(name = "bearerAuth"), @SecurityRequirement(name = "X-API-KEY")})
@Tag(name = "Course", description = "Endpoints to manager courses")
@RequiredArgsConstructor
public class CourseController {

  private final CreateCourseUseCase createCourseUseCase;
  private final CourseService service;
  private final ReviewService reviewService;
  private final CoursePresentationMapper coursePresentationMapper;

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(
      summary = "Find course by id",
      description = "Find course by id",
      tags = {"Course"},
      method = "GET")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Success",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CourseDetailsProjection.class))),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)
      })
  public ResponseEntity<CourseDTO> findById(@PathVariable UUID id) {
    return ResponseEntity.ok(service.findById(id));
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(
      summary = "Find all courses",
      description = "Find all courses with pagination sorted by total reviews score",
      tags = {"Course"},
      method = "GET")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Success",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CustomPagedResponse.class))),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)
      })
  public ResponseEntity<CustomPagedResponse<CourseResponseDTO>> findAll(
      @RequestParam(name = "page", defaultValue = "0") Integer page,
      @RequestParam(name = "size", defaultValue = "10") Integer size,
      @RequestParam(name = "direction", defaultValue = "averageScore,asc") String direction) {

    String[] sortOptions = direction.split(",");
    Direction sortDirection =
        "desc".equalsIgnoreCase(sortOptions[1]) ? Sort.Direction.DESC : Sort.Direction.ASC;
    Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortOptions[0]));

    return ResponseEntity.ok(service.findAll(pageable));
  }

  @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(
      summary = "Search courses",
      description = "Search courses by term or tags with pagination sorted by total reviews score",
      tags = {"Course"},
      method = "GET")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Success",
            content =
                @Content(
                    mediaType = "application/json",
                    array =
                        @ArraySchema(schema = @Schema(implementation = CourseProjection.class)))),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)
      })
  public ResponseEntity<CustomPagedResponse<CourseResponseDTO>> search(
      @RequestParam(name = "tags", required = false) Long[] tags,
      @RequestParam(name = "term", required = false) String term,
      @RequestParam(name = "page", defaultValue = "0") Integer page,
      @RequestParam(name = "size", defaultValue = "10") Integer size,
      @RequestParam(name = "direction", defaultValue = "averageScore,asc") String direction) {

    String[] sortOptions = direction.split(",");
    Direction sortDirection =
        "desc".equalsIgnoreCase(sortOptions[1]) ? Sort.Direction.DESC : Sort.Direction.ASC;
    Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortOptions[0]));

    if (tags != null || term != null)
      return ResponseEntity.ok(service.findByFilter(tags, term, pageable));
    return ResponseEntity.ok(service.findAll(pageable));
  }

  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(
      summary = "Create a new course",
      description = "Create a new course",
      tags = {"Course"},
      method = "POST",
      requestBody =
          @io.swagger.v3.oas.annotations.parameters.RequestBody(
              content =
                  @Content(
                      mediaType = "application/json",
                      examples =
                          @ExampleObject(
                              value =
                                  """
                                        { "title": "string", "description": "string", "capeImage": "string", "tags": [{"id": "long"}] }
                                        """))))
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "201",
            description = "Success",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ResponseDTO.class))),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)
      })
  public ResponseEntity<ResponseDTO<Object>> create(
      @Valid @RequestBody CreateCourseDTO createCourseDTO) {
    Course course = coursePresentationMapper.createDTOToDomain(createCourseDTO);
    createCourseUseCase.execute(course);
    ResponseDTO<Object> responseDTO =
        new ResponseDTO<>(
            true, CourseConstant.MESSAGE_201, CourseConstant.CODE_201, null, LocalDateTime.now());
    return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
  }

  /*
   * Reviews
   */

  @GetMapping(value = "/{id}/reviews", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(
      summary = "Find reviews by course id",
      description = "Find reviews by course id",
      tags = {"Course"},
      method = "GET")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Success",
            content =
                @Content(
                    mediaType = "application/json",
                    array =
                        @ArraySchema(schema = @Schema(implementation = ReviewProjection.class)))),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Course Not Found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)
      })
  public ResponseEntity<List<ReviewDTO>> findByCourseId(@PathVariable UUID id) {
    return ResponseEntity.ok(reviewService.findByCourseId(id));
  }

  @PostMapping(
      value = "/{id}/reviews",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(
      summary = "Create a new review",
      description = "Create a new review",
      tags = {"Course"},
      method = "POST",
      requestBody =
          @io.swagger.v3.oas.annotations.parameters.RequestBody(
              content =
                  @Content(
                      mediaType = "application/json",
                      examples =
                          @ExampleObject(
                              value =
                                  """
                                        { "score": "integer", "comment": "string" }
                                        """))))
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "201",
            description = "Success",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = GenericResponseDTO.class))),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Course Not Found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)
      })
  public ResponseEntity<GenericResponseDTO<Long>> create(
      @PathVariable UUID id, @Valid @RequestBody CreateReviewDTO data) {
    return ResponseEntity.status(HttpStatus.CREATED).body(reviewService.create(id, data));
  }
}
