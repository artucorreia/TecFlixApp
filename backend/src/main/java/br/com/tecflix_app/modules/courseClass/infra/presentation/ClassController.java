package br.com.tecflix_app.modules.courseClass.infra.presentation;

import br.com.tecflix_app.modules.courseClass.application.domain.entity.Class;
import br.com.tecflix_app.modules.courseClass.application.usecases.CreateClassUseCase;
import br.com.tecflix_app.modules.courseClass.infra.presentation.constant.ClassConstant;
import br.com.tecflix_app.modules.courseClass.infra.presentation.mapper.ClassPresentationMapper;
import br.com.tecflix_app.modules.shared.dto.v1.ResponseDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tecflix_app.modules.courseClass.infra.presentation.dtos.v1.CreateClassDTO;
import br.com.tecflix_app.modules.shared.dto.v1.GenericResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/classes")
@SecurityRequirements(
    value = {@SecurityRequirement(name = "bearerAuth"), @SecurityRequirement(name = "X-API-KEY")})
@Tag(name = "Class", description = "Endpoints to manager classes")
@RequiredArgsConstructor
public class ClassController {

  private final CreateClassUseCase createClassUseCase;
  private final ClassPresentationMapper classPresentationMapper;

  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(
      summary = "Create a new class",
      description = "Create a new class",
      tags = {"Class"},
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
                    { "title": "The Beginning", "videoUrl": "https://video/path/2", "moduleId": "1" }
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
        @ApiResponse(responseCode = "404", description = "Module Not Found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)
      })
  public ResponseEntity<ResponseDTO<Object>> create(
      @Valid @RequestBody CreateClassDTO createClassDTO) {
    Class courseClass = classPresentationMapper.map(createClassDTO);
    createClassUseCase.execute(courseClass);
    ResponseDTO<Object> response =
        new ResponseDTO<>(true, ClassConstant.MESSAGE_201, ClassConstant.CODE_201, null);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }
}
