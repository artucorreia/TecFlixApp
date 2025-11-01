package br.com.tecflix_app.modules.tag.infra.presentation;

import br.com.tecflix_app.modules.shared.dto.v1.ResponseDTO;
import br.com.tecflix_app.modules.tag.application.domain.entity.Tag;
import br.com.tecflix_app.modules.tag.application.usecases.FindAllTagsUseCase;
import br.com.tecflix_app.modules.tag.application.usecases.FindTagByIdUseCase;
import br.com.tecflix_app.modules.tag.infra.presentation.constant.TagConstant;
import br.com.tecflix_app.modules.tag.infra.presentation.dtos.v1.response.TagResponseDTO;
import br.com.tecflix_app.modules.tag.infra.presentation.mapper.TagPresentationMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.Set;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/tags")
@SecurityRequirements(
    value = {@SecurityRequirement(name = "bearerAuth"), @SecurityRequirement(name = "X-API-KEY")})
@io.swagger.v3.oas.annotations.tags.Tag(name = "Tag", description = "Endpoints to manager tags")
@RequiredArgsConstructor
public class TagController {
  // usecases
  private final FindTagByIdUseCase findTagByIdUseCase;
  private final FindAllTagsUseCase findAllTagsUseCase;

  // mappers
  private final TagPresentationMapper tagPresentationMapper;

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(
      summary = "Find tag by id",
      description = "Find tag by id",
      tags = {"Tag"},
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
  public ResponseEntity<ResponseDTO<TagResponseDTO>> findById(@PathVariable Long id) {
    Tag tag = findTagByIdUseCase.execute(id);
    TagResponseDTO tagResponseDTO = tagPresentationMapper.map(tag);
    ResponseDTO<TagResponseDTO> response =
        new ResponseDTO<>(true, null, TagConstant.CODE_200, tagResponseDTO);
    return ResponseEntity.ok(response);
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(
      summary = "Find all tags",
      description = "Find all tags",
      tags = {"Tag"},
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
  public ResponseEntity<ResponseDTO<Set<TagResponseDTO>>> findAll() {
    Set<Tag> tags = findAllTagsUseCase.execute();
    Set<TagResponseDTO> tagResponseDTOS = tagPresentationMapper.map(tags);
    ResponseDTO<Set<TagResponseDTO>> response =
        new ResponseDTO<>(true, null, TagConstant.CODE_200, tagResponseDTOS);
    return ResponseEntity.ok(response);
  }
}
