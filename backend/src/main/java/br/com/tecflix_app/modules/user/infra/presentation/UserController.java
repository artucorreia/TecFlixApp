package br.com.tecflix_app.modules.user.infra.presentation;

import br.com.tecflix_app.modules.course.application.domain.entity.Course;
import br.com.tecflix_app.modules.course.application.usecases.FindCoursesProfileByUserIdUseCase;
import br.com.tecflix_app.modules.course.infra.presentation.dtos.v1.CourseUserProfileResponseDTO;
import br.com.tecflix_app.modules.course.infra.presentation.mapper.CoursePresentationMapper;
import br.com.tecflix_app.modules.professorData.application.domain.entity.ProfessorData;
import br.com.tecflix_app.modules.professorData.application.usecases.FindAuthenticatedUserProfessorDataByUserIdUseCase;
import br.com.tecflix_app.modules.professorData.application.usecases.FindProfessorDataProfileByUserIdUseCase;
import br.com.tecflix_app.modules.professorData.infra.presentation.dtos.v1.AuthenticatedUserProfessorDataResponseDTO;
import br.com.tecflix_app.modules.professorData.infra.presentation.dtos.v1.ProfessorDataUserProfileResponseDTO;
import br.com.tecflix_app.modules.professorData.infra.presentation.mapper.ProfessorDataPresentationMapper;
import br.com.tecflix_app.modules.shared.dto.v1.ResponseDTO;
import br.com.tecflix_app.modules.social.application.domain.entity.Social;
import br.com.tecflix_app.modules.social.application.usecases.FindSocialsAuthenticatedUserByUserIdUseCase;
import br.com.tecflix_app.modules.social.application.usecases.FindSocialsProfileByUserIdUseCase;
import br.com.tecflix_app.modules.social.infra.presentation.dtos.v1.AuthenticatedUserSocialResponseDTO;
import br.com.tecflix_app.modules.social.infra.presentation.dtos.v1.SocialUserProfileResponseDTO;
import br.com.tecflix_app.modules.social.infra.presentation.mapper.SocialPresentationMapper;
import br.com.tecflix_app.modules.user.application.domain.entity.User;
import br.com.tecflix_app.modules.user.application.usecases.CreateProfessorUseCase;
import br.com.tecflix_app.modules.user.application.usecases.FindMeUseCase;
import br.com.tecflix_app.modules.user.infra.presentation.dtos.v1.AuthenticatedUserResponseDTO;
import br.com.tecflix_app.modules.user.infra.presentation.dtos.v1.ProfessorProfileResponseDTO;
import br.com.tecflix_app.modules.user.infra.presentation.dtos.v1.RegisterProfessorDTO;
import br.com.tecflix_app.modules.shared.dto.v1.GenericResponseDTO;
import br.com.tecflix_app.modules.user.infra.persistence.projections.UserAccountProjection;
import br.com.tecflix_app.modules.user.infra.persistence.projections.UserProfileProjection;
import br.com.tecflix_app.modules.user.infra.presentation.constant.UserConstant;
import br.com.tecflix_app.modules.user.infra.presentation.mapper.UserPresentationMapper;
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

import java.util.List;
import java.util.Set;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@SecurityRequirements(
    value = {@SecurityRequirement(name = "bearerAuth"), @SecurityRequirement(name = "X-API-KEY")})
@Tag(name = "User", description = "Endpoints to manager users")
@RequiredArgsConstructor
public class UserController {
  // usecases
  private final FindMeUseCase findMeUseCase;
  private final FindAuthenticatedUserProfessorDataByUserIdUseCase
      findAuthenticatedUserProfessorDataByUserIdUseCase;
  private final FindSocialsAuthenticatedUserByUserIdUseCase
      findSocialsAuthenticatedUserByUserIdUseCase;
  private final FindProfessorDataProfileByUserIdUseCase findProfessorDataProfileByUserIdUseCase;
  private final FindSocialsProfileByUserIdUseCase findSocialsProfileByUserIdUseCase;
  private final FindCoursesProfileByUserIdUseCase findCoursesProfileByUserIdUseCase;
  private final CreateProfessorUseCase createProfessorUseCase;

  // mappers
  private final UserPresentationMapper userPresentationMapper;
  private final SocialPresentationMapper socialPresentationMapper;
  private final ProfessorDataPresentationMapper professorDataPresentationMapper;
  private final CoursePresentationMapper coursePresentationMapper;

  @GetMapping(value = "/me", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(
      summary = "Find user by access token",
      description = "Find user data by access token",
      tags = {"User"},
      method = "GET")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Success",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = UserAccountProjection.class))),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)
      })
  public ResponseEntity<ResponseDTO<AuthenticatedUserResponseDTO>> findMe() {
    User user = findMeUseCase.execute();
    AuthenticatedUserResponseDTO authenticatedUserResponseDTO = userPresentationMapper.map(user);

    ProfessorData professorData =
        findAuthenticatedUserProfessorDataByUserIdUseCase.execute(user.getId());
    AuthenticatedUserProfessorDataResponseDTO authenticatedUserProfessorDataResponseDTO =
        professorDataPresentationMapper.toAuthenticatedUserProfessorDataResponseDTO(professorData);
    authenticatedUserResponseDTO.setProfessorData(authenticatedUserProfessorDataResponseDTO);
    Boolean userIsProfessor = authenticatedUserResponseDTO.getProfessorData() != null;
    authenticatedUserResponseDTO.setIsProfessor(userIsProfessor);

    if (userIsProfessor) {
      List<Social> socials = findSocialsAuthenticatedUserByUserIdUseCase.execute(user.getId());
      List<AuthenticatedUserSocialResponseDTO> authenticatedUserSocialResponseDTOS =
          socialPresentationMapper.mapAuthenticated(socials);
      authenticatedUserResponseDTO.setSocials(authenticatedUserSocialResponseDTOS);
    }
    ResponseDTO<AuthenticatedUserResponseDTO> response =
        new ResponseDTO<>(true, null, UserConstant.CODE_200, authenticatedUserResponseDTO);
    return ResponseEntity.ok(response);
  }

  @GetMapping(value = "/profile/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(
      summary = "Find professor profile by id",
      description = "Find professor profile by id",
      tags = {"User"},
      method = "GET")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Success",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = UserProfileProjection.class))),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)
      })
  public ResponseEntity<ResponseDTO<ProfessorProfileResponseDTO>> findProfileById(
      @PathVariable UUID id) {
    ProfessorData professorData = findProfessorDataProfileByUserIdUseCase.execute(id);
    ProfessorDataUserProfileResponseDTO professorDataUserProfileResponseDTO =
        professorDataPresentationMapper.map(professorData);
    List<Social> socials = findSocialsProfileByUserIdUseCase.execute(id);
    List<SocialUserProfileResponseDTO> socialUserProfileResponseDTOS =
        socialPresentationMapper.map(socials);
    List<Course> courses = findCoursesProfileByUserIdUseCase.execute(id);
    List<CourseUserProfileResponseDTO> courseUserProfileResponseDTOS =
        coursePresentationMapper.map(courses);
    ProfessorProfileResponseDTO professorProfileResponseDTO =
        new ProfessorProfileResponseDTO(
            professorDataUserProfileResponseDTO,
            socialUserProfileResponseDTOS,
            courseUserProfileResponseDTOS);
    ResponseDTO<ProfessorProfileResponseDTO> response =
        new ResponseDTO<>(true, null, UserConstant.CODE_200, professorProfileResponseDTO);
    return ResponseEntity.ok(response);
  }

  @PostMapping(
      value = "/register-professor",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(
      summary = "Register a user as professor",
      description = "Register a user as professor",
      tags = {"User"},
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
                            { "professorData": { "occupationId": 1, "otherOccupation": "XYZ", "biography": "Um pouco sobre a minha história...", "birthdate": "1990-09-20", "genderId": 1, "otherGender": "XYZ", "phoneNumber": "5582988776655", "profileImageUrl": "https://dominio/image/path/1" }, "socials": [{"socialNameId": 1, "url": "https://x.com/username"}] }
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
  public ResponseEntity<ResponseDTO<Object>> registerAsProfessor(
      @Valid @RequestBody RegisterProfessorDTO registerProfessorDTO) {
    ProfessorData professorData =
        professorDataPresentationMapper.map(registerProfessorDTO.getProfessorData());
    Set<Social> socials = socialPresentationMapper.map(registerProfessorDTO.getSocials());
    createProfessorUseCase.execute(professorData, socials);
    ResponseDTO<Object> response =
        new ResponseDTO<>(true, UserConstant.MESSAGE_201, UserConstant.CODE_201, null);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }
}
