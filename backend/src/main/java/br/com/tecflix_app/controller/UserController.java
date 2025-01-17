package br.com.tecflix_app.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.tecflix_app.data.DTO.v1.create.RegisterProfessorDTO;
import br.com.tecflix_app.data.DTO.v1.response.GenericResponseDTO;
import br.com.tecflix_app.data.DTO.v1.response.UserDTO;
import br.com.tecflix_app.projection.UserAccountProjection;
import br.com.tecflix_app.projection.UserBasicProjection;
import br.com.tecflix_app.projection.UserProfileProjection;
import br.com.tecflix_app.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "User", description = "Endpoints to manager users")
public class UserController {
        private final UserService service;

        @Autowired
        public UserController(
                        UserService service) {
                this.service = service;
        }

        @GetMapping(value = "/me", produces = MediaType.APPLICATION_JSON_VALUE)
        @Operation(summary = "Find user by access token", description = "Find user data by access token", tags = {
                        "User" }, method = "GET")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserAccountProjection.class))),
                        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                        @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
                        @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)
        })
        public ResponseEntity<UserDTO> findMe() {
                return ResponseEntity.ok(service.findMe());
        }

        @GetMapping(value = "/profile/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
        @Operation(summary = "Find user profile by id", description = "Find user profile by id", tags = {
                        "User" }, method = "GET")

        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserProfileProjection.class))),
                        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                        @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
                        @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)
        })
        public ResponseEntity<UserDTO> findProfileById(@PathVariable UUID id) {
                return ResponseEntity.ok(service.findProfileById(id));
        }

        @GetMapping(value = "/find", produces = MediaType.APPLICATION_JSON_VALUE)
        @Operation(summary = "Find user id by email", description = "Find user id by email", tags = {
                        "User" }, method = "GET")

        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserBasicProjection.class))),
                        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                        @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
                        @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)
        })
        public ResponseEntity<UserDTO> findProfileById(@RequestParam(required = true) String email) {
                return ResponseEntity.ok(service.findIdByEmail(email));
        }

        @PostMapping(value = "/{userId}/make-professor", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        @Operation(summary = "Change user type to professor", description = "Change user type to professor", tags = {
                        "User" }, method = "POST", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/json", examples = @ExampleObject(value = """
                                        { "professorData": { "cpf": "string", "birthDate": "1990-10-10", "gender": "MALE", "contact": "string", "occupation": "PROFESSOR", "biography": "string", "profileImage": "string" }, "socials": [{"id": "long"}] }
                                        """))))
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponseDTO.class))),
                        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                        @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
                        @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)
        })
        public ResponseEntity<GenericResponseDTO<UUID>> createProfessor(@PathVariable UUID userId,
                        @Valid @RequestBody RegisterProfessorDTO data) {
                UserDTO user = new UserDTO();
                user.setId(userId);
                return ResponseEntity.ok(service.createProfessor(user, data));
        }
}
