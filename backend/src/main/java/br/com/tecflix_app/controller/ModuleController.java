package br.com.tecflix_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tecflix_app.data.DTO.v1.create.CreateModuleDTO;
import br.com.tecflix_app.data.DTO.v1.response.GenericResponseDTO;
import br.com.tecflix_app.data.DTO.v1.response.ModuleDTO;
import br.com.tecflix_app.service.ModuleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/modules")
@Tag(name = "Module", description = "Endpoints to manager modules")
public class ModuleController {

    private final ModuleService service;
    
    @Autowired
    public ModuleController(ModuleService service) { this.service = service; }

    @GetMapping(
        value = "/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE 
    )
    @Operation(
        summary = "Find module by id",
        description = "Find module by id",
        tags = {"Module"},
        method = "GET"
    )
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                description = "Success",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ModuleDTO.class)
                )
            ),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)
        }
    )
    public ResponseEntity<ModuleDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }
    
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Find all modules",
        description = "Find all modules",
        tags = {"Module"},
        method = "GET"
    )
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                description = "Success",
                content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = ModuleDTO.class))
                )
            ),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)
        }
    )
    public ResponseEntity<List<ModuleDTO>> findAll() {
        return ResponseEntity.ok(service.findByAll());
    }

    @PostMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
        summary = "Create a new module",
        description = "Create a new module",
        tags = {"Module"},
        method = "POST",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    value = """
                            { "title": "string", "course": {"id": "uuid"} }
                            """
                )
            )
        )
    )
    @ApiResponses(
        value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Success",
                    content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = GenericResponseDTO.class)
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Course Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)
        }
    )
    public ResponseEntity<GenericResponseDTO<Long>> create(@Valid @RequestBody CreateModuleDTO data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(data));
    }
}
