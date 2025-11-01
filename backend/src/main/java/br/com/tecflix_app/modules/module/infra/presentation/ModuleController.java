package br.com.tecflix_app.modules.module.infra.presentation;

import br.com.tecflix_app.modules.module.application.domain.entity.Module;
import br.com.tecflix_app.modules.module.application.usecases.CreateModuleUseCase;
import br.com.tecflix_app.modules.module.infra.presentation.constant.ModuleConstant;
import br.com.tecflix_app.modules.module.infra.presentation.mapper.ModulePresentationMapper;
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

import br.com.tecflix_app.modules.module.infra.presentation.dtos.v1.CreateModuleDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/modules")
@SecurityRequirements(
    value = {@SecurityRequirement(name = "bearerAuth"), @SecurityRequirement(name = "X-API-KEY")})
@Tag(name = "Module", description = "Endpoints to manager modules")
@RequiredArgsConstructor
public class ModuleController {
  // usecases
  private final CreateModuleUseCase createModuleUseCase;

  // mappers
  private final ModulePresentationMapper modulePresentationMapper;

  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(
      summary = "Create a new module",
      description = "Create a new module",
      tags = {"Module"},
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
                    { "title": "Basic Module", "courseId": "6f0af909-3d33-46db-856a-fc7e780a64ac" }
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
        @ApiResponse(responseCode = "404", description = "Course Not Found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)
      })
  public ResponseEntity<ResponseDTO<Object>> create(
      @Valid @RequestBody CreateModuleDTO createModuleDTO) {
    Module module = modulePresentationMapper.map(createModuleDTO);
    createModuleUseCase.execute(module);
    ResponseDTO<Object> response =
        new ResponseDTO<>(true, ModuleConstant.MESSAGE_201, ModuleConstant.CODE_201, null);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }
}
