package br.com.tecflix_app.modules.course.infra.presentation;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import br.com.tecflix_app.modules.course.application.domain.entity.Course;
import br.com.tecflix_app.modules.course.application.usecases.CreateCourseUseCase;
import br.com.tecflix_app.modules.course.application.usecases.FindAllCoursesUseCase;
import br.com.tecflix_app.modules.course.application.usecases.FindCourseDetailsByIdUseCase;
import br.com.tecflix_app.modules.course.application.usecases.FindCoursesBySearchUseCase;
import br.com.tecflix_app.modules.course.infra.presentation.constant.CourseConstant;
import br.com.tecflix_app.modules.course.infra.presentation.mapper.CoursePresentationMapper;
import br.com.tecflix_app.modules.courseClass.application.domain.entity.Class;
import br.com.tecflix_app.modules.courseClass.application.usecases.FindClassesByModuleIdUseCase;
import br.com.tecflix_app.modules.courseClass.infra.presentation.dtos.v1.ClassDTO;
import br.com.tecflix_app.modules.courseClass.infra.presentation.mapper.ClassPresentationMapper;
import br.com.tecflix_app.modules.module.application.domain.entity.Module;
import br.com.tecflix_app.modules.module.application.usecases.FindModulesByCourseIdUseCase;
import br.com.tecflix_app.modules.module.infra.presentation.dtos.v1.ModuleResponseDTO;
import br.com.tecflix_app.modules.module.infra.presentation.mapper.ModulePresentationMapper;
import br.com.tecflix_app.modules.review.application.domain.entity.Review;
import br.com.tecflix_app.modules.review.application.usecases.CreateReviewUseCase;
import br.com.tecflix_app.modules.review.application.usecases.FindReviewsByCourseIdUseCase;
import br.com.tecflix_app.modules.review.infra.presentation.dtos.v1.ReviewResponseDTO;
import br.com.tecflix_app.modules.review.infra.presentation.mapper.ReviewPresentationMapper;
import br.com.tecflix_app.modules.shared.application.domain.entity.CustomPageResult;
import br.com.tecflix_app.modules.shared.dto.v1.CustomPageResponseDTO;
import br.com.tecflix_app.modules.shared.dto.v1.ResponseDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import lombok.RequiredArgsConstructor;
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

import br.com.tecflix_app.modules.course.infra.presentation.dtos.v1.CreateCourseDTO;
import br.com.tecflix_app.modules.review.infra.presentation.dtos.v1.CreateReviewDTO;
import br.com.tecflix_app.modules.course.infra.presentation.dtos.v1.CourseResponseDTO;
import br.com.tecflix_app.modules.shared.dto.v1.GenericResponseDTO;
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
  // usecases
  private final FindCourseDetailsByIdUseCase findCourseDetailsByIdUseCase;
  private final FindAllCoursesUseCase findAllCoursesUseCase;
  private final FindCoursesBySearchUseCase findCoursesBySearchUseCase;
  private final CreateCourseUseCase createCourseUseCase;
  private final FindModulesByCourseIdUseCase findModulesByCourseIdUseCase;
  private final FindClassesByModuleIdUseCase findClassesByModuleIdUseCase;
  private final FindReviewsByCourseIdUseCase findReviewsByCourseIdUseCase;
  private final CreateReviewUseCase createReviewUseCase;

  // services
  private final CourseService service;
  private final ReviewService reviewService;

  // mappers
  private final CoursePresentationMapper coursePresentationMapper;
  private final ModulePresentationMapper modulePresentationMapper;
  private final ClassPresentationMapper classPresentationMapper;
  private final ReviewPresentationMapper reviewPresentationMapper;

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
                    schema = @Schema(implementation = ResponseDTO.class))),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)
      })
  public ResponseEntity<ResponseDTO<CourseResponseDTO>> findById(@PathVariable UUID id) {
    Course course = findCourseDetailsByIdUseCase.execute(id);
    Set<Module> modules = findModulesByCourseIdUseCase.execute(course.getId());
    CourseResponseDTO courseResponseDTO = coursePresentationMapper.map(course);
    Set<ModuleResponseDTO> moduleResponseDTOS = modulePresentationMapper.map(modules);
    moduleResponseDTOS.forEach(
        moduleResponseDTO -> {
          Set<Class> classes = findClassesByModuleIdUseCase.execute(moduleResponseDTO.getId());
          Set<ClassDTO> classDTOS = classPresentationMapper.map(classes);
          moduleResponseDTO.setClasses(classDTOS);
        });
    courseResponseDTO.setModules(moduleResponseDTOS);
    ResponseDTO<CourseResponseDTO> response =
        new ResponseDTO<>(true, null, CourseConstant.CODE_200, courseResponseDTO);
    return ResponseEntity.ok(response);
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(
      summary = "Find all courses",
      description = "Find all courses with pagination sorting, sorted by average score by default",
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
                    schema = @Schema(implementation = ResponseDTO.class))),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)
      })
  public ResponseEntity<ResponseDTO<CustomPageResponseDTO<CourseResponseDTO>>> findAll(
      @RequestParam(name = "page", defaultValue = "0") Integer page,
      @RequestParam(name = "size", defaultValue = "10") Integer size,
      @RequestParam(name = "direction", defaultValue = "averageScore,asc") String direction) {
    String[] sortOptions = direction.split(",");
    CustomPageResult<Course> coursesCustomPage =
        findAllCoursesUseCase.execute(page, size, sortOptions[0], sortOptions[1]);

    List<CourseResponseDTO> content =
        coursesCustomPage.getContent().stream().map(coursePresentationMapper::map).toList();
    CustomPageResponseDTO<CourseResponseDTO> courseResponseDTOCustomPage =
        new CustomPageResponseDTO<>(
            content,
            coursesCustomPage.getPageNumber(),
            coursesCustomPage.getPageSize(),
            coursesCustomPage.getTotalElements(),
            coursesCustomPage.getTotalPages(),
            coursesCustomPage.isHasNext(),
            coursesCustomPage.isHasPrevious());
    ResponseDTO<CustomPageResponseDTO<CourseResponseDTO>> response =
        new ResponseDTO<>(true, null, CourseConstant.CODE_200, courseResponseDTOCustomPage);
    return ResponseEntity.ok(response);
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
                    array = @ArraySchema(schema = @Schema(implementation = ResponseDTO.class)))),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)
      })
  public ResponseEntity<ResponseDTO<CustomPageResponseDTO<CourseResponseDTO>>> search(
      @RequestParam(name = "tags", required = false) Long[] tags,
      @RequestParam(name = "term", required = false) String term,
      @RequestParam(name = "page", defaultValue = "0") Integer page,
      @RequestParam(name = "size", defaultValue = "10") Integer size,
      @RequestParam(name = "direction", defaultValue = "averageScore,asc") String direction) {

    String[] sortOptions = direction.split(",");
    CustomPageResult<Course> courseCustomPageResult =
        findCoursesBySearchUseCase.execute(tags, term, page, size, sortOptions[0], sortOptions[1]);
    List<CourseResponseDTO> content =
        courseCustomPageResult.getContent().stream().map(coursePresentationMapper::map).toList();
    CustomPageResponseDTO<CourseResponseDTO> courseResponseDTOCustomPageResponseDTO =
        new CustomPageResponseDTO<>(
            content,
            courseCustomPageResult.getPageNumber(),
            courseCustomPageResult.getPageSize(),
            courseCustomPageResult.getTotalElements(),
            courseCustomPageResult.getTotalPages(),
            courseCustomPageResult.isHasNext(),
            courseCustomPageResult.isHasPrevious());
    ResponseDTO<CustomPageResponseDTO<CourseResponseDTO>> response =
        new ResponseDTO<>(
            true, null, CourseConstant.CODE_200, courseResponseDTOCustomPageResponseDTO);
    return ResponseEntity.ok(response);
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
                                        { "title": "My Course's Title", "description": "from basico to advanced", "capeImageUrl": "https://image/path/2", "tagIds": [1, 2, 3] }
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
    Course course = coursePresentationMapper.map(createCourseDTO);
    createCourseUseCase.execute(course);
    ResponseDTO<Object> responseDTO =
        new ResponseDTO<>(true, CourseConstant.MESSAGE_201, CourseConstant.CODE_201, null);
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
                    array = @ArraySchema(schema = @Schema(implementation = ResponseDTO.class)))),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Course Not Found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)
      })
  public ResponseEntity<ResponseDTO<List<ReviewResponseDTO>>> findByCourseId(
      @PathVariable UUID id) {
    List<Review> reviews = findReviewsByCourseIdUseCase.execute(id);
    List<ReviewResponseDTO> reviewResponseDTOS =
        reviews.stream().map(reviewPresentationMapper::toResponseDTO).toList();

    ResponseDTO<List<ReviewResponseDTO>> response =
        new ResponseDTO<>(true, null, CourseConstant.CODE_200, reviewResponseDTOS);
    return ResponseEntity.ok(response);
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
                                        { "score": 5, "comment": "Amazing!" }
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
        @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)
      })
  public ResponseEntity<ResponseDTO<Object>> create(
      @PathVariable UUID id, @Valid @RequestBody CreateReviewDTO createReviewDTO) {
    Review review = reviewPresentationMapper.toDomain(createReviewDTO);
    createReviewUseCase.execute(id, review);
    ResponseDTO<Object> response =
        new ResponseDTO<>(true, CourseConstant.MESSAGE_201, CourseConstant.CODE_201, null);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }
}
