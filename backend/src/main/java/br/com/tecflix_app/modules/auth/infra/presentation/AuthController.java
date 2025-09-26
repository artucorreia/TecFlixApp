package br.com.tecflix_app.modules.auth.infra.presentation;

import br.com.tecflix_app.modules.auth.infra.presentation.constant.AuthConstant;
import br.com.tecflix_app.modules.auth.infra.presentation.dtos.v1.AuthenticationDTO;
import br.com.tecflix_app.modules.auth.infra.presentation.dtos.v1.NewPasswordDTO;
import br.com.tecflix_app.modules.auth.infra.presentation.dtos.v1.RegisterDTO;
import br.com.tecflix_app.modules.auth.infra.presentation.dtos.v1.TokenResponseDTO;
import br.com.tecflix_app.modules.auth.infra.presentation.mapper.AuthPresentationMapper;
import br.com.tecflix_app.modules.auth.infra.security.refreshToken.RefreshTokenService;
import br.com.tecflix_app.modules.auth.infra.security.jwt.TokenService;
import br.com.tecflix_app.modules.auth.infra.security.jwt.CustomUserDetails;
import br.com.tecflix_app.modules.auth.infra.presentation.dtos.v1.RefreshTokenDTO;
import br.com.tecflix_app.modules.shared.dto.v1.ResponseDTO;
import br.com.tecflix_app.modules.shared.exception.auth.WrongPasswordException;
import br.com.tecflix_app.modules.user.application.domain.entity.User;
import br.com.tecflix_app.modules.user.application.usecases.RegisterUserUseCase;
import br.com.tecflix_app.service.EmailCodeService;
import br.com.tecflix_app.service.UserService;
import br.com.tecflix_app.modules.shared.dto.v1.GenericResponseDTO;
import br.com.tecflix_app.modules.shared.exception.auth.InactiveUserException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.UUID;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@SecurityRequirements(
    value = {@SecurityRequirement(name = "bearerAuth"), @SecurityRequirement(name = "X-API-KEY")})
@Tag(name = "Authentication", description = "Endpoints for registration and login to the system")
@RequiredArgsConstructor
public class AuthController {

  private final UserService userService;
  private final EmailCodeService emailCodeService;
  private final AuthenticationManager authenticationManager;
  private final TokenService tokenService;
  private final RefreshTokenService refreshTokenService;

  private final RegisterUserUseCase registerUserUseCase;
  private final AuthPresentationMapper authPresentationMapper;

  @PostMapping(
      value = "/login",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(
      summary = "Log into the system",
      description = "Log into the system",
      tags = {"Authentication"},
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
                                        { "email": "john@gmail.com", "password": "12345678" }
                                        """))))
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Success",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = TokenResponseDTO.class))),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)
      })
  public ResponseEntity<TokenResponseDTO> login(@Valid @RequestBody AuthenticationDTO data) {
    if (!userService.findActiveByEmail(data.getEmail()))
      throw new InactiveUserException("O usuário está inativo");

    UsernamePasswordAuthenticationToken usernamePassword =
        new UsernamePasswordAuthenticationToken(data.getEmail(), data.getPassword());

    TokenResponseDTO token;
    try {
      Authentication auth = authenticationManager.authenticate(usernamePassword);
      CustomUserDetails customUserDetails = (CustomUserDetails) auth.getPrincipal();
      token = tokenService.generateToken(customUserDetails.getUser().getId());
    } catch (AuthenticationException e) {
      throw new WrongPasswordException("Senha incorreta");
    }

    return ResponseEntity.ok(token);
  }

  @PostMapping(
      value = "/refresh-token",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(
      summary = "Refresh token",
      description = "Refresh token",
      tags = {"Authentication"},
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
                                        { "token": "string" }
                                        """))))
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Success",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = TokenResponseDTO.class))),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)
      })
  public ResponseEntity<TokenResponseDTO> refreshToken(@Valid @RequestBody RefreshTokenDTO data) {
    UUID userId = refreshTokenService.resolve(data.getToken());
    return ResponseEntity.ok(tokenService.generateToken(userId));
  }

  @PostMapping(
      value = "/register",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(
      summary = "Register in the system",
      description = "Register a new user in the system",
      tags = {"Authentication"},
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
                                        { "name": "John Kevin", "email": "john@gmail.com", "password": "12345678" }
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
        @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)
      })
  public ResponseEntity<ResponseDTO<Object>> register(@Valid @RequestBody RegisterDTO data) {
    User user = authPresentationMapper.map(data);
    registerUserUseCase.execute(user);
    ResponseDTO<Object> responseDTO =
        new ResponseDTO<>(
            true, AuthConstant.REGISTER_MESSAGE_201, AuthConstant.REGISTER_CODE_201, null);
    return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
  }

  @PostMapping(value = "/send-code/{userId}")
  @Operation(
      summary = "Send email code",
      description = "Send email code to validate email or reset password",
      tags = {"Authentication"},
      method = "POST")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
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
  public ResponseEntity<GenericResponseDTO<Long>> sendEmailCode(
      @PathVariable UUID userId,
      @RequestParam(name = "resetPassword", required = false, defaultValue = "false")
          boolean resetPassword) {
    return ResponseEntity.ok(emailCodeService.create(userId, resetPassword));
  }

  @PostMapping(value = "/validate-code")
  @Operation(
      summary = "Validate email code",
      description = "Validate email code",
      tags = {"Authentication"},
      method = "POST")
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
  public ResponseEntity<GenericResponseDTO<UUID>> validateEmailCode(
      @RequestParam(required = true) String code, @RequestParam(required = true) UUID userId) {
    return ResponseEntity.status(HttpStatus.CREATED).body(emailCodeService.validate(code, userId));
  }

  @PostMapping(
      value = "/change-password",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(
      summary = "Change user password",
      description = "Change user password",
      tags = {"Authentication"},
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
                                        { "newPassword": "string" }
                                        """))))
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
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
  public ResponseEntity<GenericResponseDTO<UUID>> changePassword(
      @Valid @RequestBody NewPasswordDTO data) {
    return ResponseEntity.ok(userService.changePassword(data));
  }

  @PostMapping(
      value = "/reset-password",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(
      summary = "Reset user password",
      description = "Reset user password",
      tags = {"Authentication"},
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
                                        { "newPassword": "string" }
                                        """))))
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
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
  public ResponseEntity<GenericResponseDTO<UUID>> resetPassword(
      @RequestParam(required = true) String code,
      @RequestParam(required = true) UUID userId,
      @Valid @RequestBody NewPasswordDTO data) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(emailCodeService.validate(code, userId, data));
  }
}
