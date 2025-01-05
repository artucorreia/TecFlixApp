package br.com.tecflix_app.controller;

import jakarta.validation.Valid;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import br.com.tecflix_app.data.DTO.v1.auth.AuthenticationDTO;
import br.com.tecflix_app.data.DTO.v1.auth.RefreshTokenDTO;
import br.com.tecflix_app.data.DTO.v1.auth.RegisterDTO;
import br.com.tecflix_app.data.DTO.v1.auth.TokenDTO;
import br.com.tecflix_app.data.DTO.v1.response.GenericResponseDTO;
import br.com.tecflix_app.exception.auth.InactiveUserException;
import br.com.tecflix_app.exception.auth.WrongPasswordException;
import br.com.tecflix_app.model.User;
import br.com.tecflix_app.service.EmailCodeService;
import br.com.tecflix_app.service.UserService;
import br.com.tecflix_app.service.auth.jwt.RefreshTokenService;
import br.com.tecflix_app.service.auth.jwt.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Endpoints for registration and login to the system")
public class AuthController {
    
    private final UserService userService;
    private final EmailCodeService emailCodeService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final RefreshTokenService refreshTokenService;

    @Autowired
    public AuthController(
        UserService userService,
        EmailCodeService emailCodeService,
        AuthenticationManager authenticationManager,
        TokenService tokenService,
        RefreshTokenService refreshTokenService
    ) {
        this.userService = userService;
        this.emailCodeService = emailCodeService;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping(
        value = "/login",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
        summary = "Log into the system",
        description = "Log into the system",
        tags = {"Authentication"},
        method = "POST",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    value = """
                            { "email": "string", "password": "string" }
                            """
                )
            )
        )
    )
    @ApiResponses(
        value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = TokenDTO.class)
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)
        }
    )
    public ResponseEntity<TokenDTO> login(@Valid @RequestBody AuthenticationDTO data) {
        if (!userService.findActiveByEmail(data.getEmail())) 
            throw new InactiveUserException("O usuário está inativo");

        UsernamePasswordAuthenticationToken usernamePassword =
            new UsernamePasswordAuthenticationToken(data.getEmail(), data.getPassword());

        TokenDTO token;
        try {
            Authentication auth = authenticationManager.authenticate(usernamePassword);
            User user = (User) auth.getPrincipal();
            token = tokenService.generateToken(user.getId());
        }
        catch (AuthenticationException e){
            throw new WrongPasswordException("Senha incorreta");
        }

        return ResponseEntity.ok(token);
    }
    
    @PostMapping(
        value = "/refresh-token",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
        summary = "Refresh token",
        description = "Refresh token",
        tags = {"Authentication"},
        method = "POST",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    value = """
                            { "token": "string" }
                            """
                )
            )
        )
    )
    @ApiResponses(
        value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = TokenDTO.class)
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)
        }
    )
    public ResponseEntity<TokenDTO> refreshToken(@Valid @RequestBody RefreshTokenDTO data) {
        UUID userId = refreshTokenService.resolve(data.getToken());        
        return ResponseEntity.ok(tokenService.generateToken(userId));
    }
    
    @PostMapping(
        value = "/register",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
        summary = "Register in the system",
        description = "Register in the system",
        tags = {"Authentication"},
        method = "POST",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    value = """
                            { "name": "string", "email": "string", "password": "string" }
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
            @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)
        }
    )
    public ResponseEntity<GenericResponseDTO<UUID>> register(@Valid @RequestBody RegisterDTO data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.register(data));
    }
    
    @PostMapping(value = "/send-code/{userId}")
    @Operation(
        summary = "Send email code",
        description = "Send email code to validate email",
        tags = {"Authentication"},
        method = "POST"
    )
    @ApiResponses(
        value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = GenericResponseDTO.class)
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)
        }
    )
    public ResponseEntity<GenericResponseDTO<Long>> sendEmailCode(@PathVariable UUID userId) {
        return ResponseEntity.ok().body(emailCodeService.create(userId));
    }

    @PostMapping(value = "/validate-code/{code}")
    @Operation(
        summary = "Validate email code",
        description = "Validate email code",
        tags = {"Authentication"},
        method = "POST"
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
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)
        }
    )
    public ResponseEntity<GenericResponseDTO<UUID>> validateEmailCode(@PathVariable String code) {
        return ResponseEntity.status(HttpStatus.CREATED).body(emailCodeService.validate(code));
    }
}
