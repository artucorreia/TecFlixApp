package br.com.tecflix_app.service;

import br.com.tecflix_app.mapper.contract.IMapperService;
import br.com.tecflix_app.modules.auth.infra.dtos.v1.request.NewPasswordRequest;
import br.com.tecflix_app.modules.auth.infra.dtos.v1.request.RegisterRequest;
import br.com.tecflix_app.modules.auth.application.usecases.jwt.TokenService;
import br.com.tecflix_app.modules.user.application.domain.enums.Role;
import br.com.tecflix_app.modules.user.infra.persistence.UserEntity;
import br.com.tecflix_app.modules.user.infra.persistence.UserRepository;
import br.com.tecflix_app.modules.user.infra.dtos.v1.RegisterProfessorDTO;
import br.com.tecflix_app.modules.user.infra.dtos.v1.UserDTO;
import br.com.tecflix_app.service.util.UserValidatorService;
import br.com.tecflix_app.shared.dto.v1.GenericResponseDTO;
import br.com.tecflix_app.shared.exception.auth.UserAlreadyIsActive;
import br.com.tecflix_app.shared.exception.general.InaccessibleResource;
import br.com.tecflix_app.shared.exception.general.ResourceNotFoundException;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
  private final Logger LOGGER = Logger.getLogger(UserService.class.getName());

  private final UserRepository repository;
  private final UserValidatorService validatorService;
  private final TokenService tokenService;
  private final ProfessorDataService professorDataService;
  private final SocialService socialService;
  private final IMapperService mapper;

  @Autowired
  public UserService(
      UserRepository repository,
      UserValidatorService validatorService,
      TokenService tokenService,
      ProfessorDataService professorDataService,
      SocialService socialService,
      IMapperService mapper) {
    this.repository = repository;
    this.validatorService = validatorService;
    this.tokenService = tokenService;
    this.professorDataService = professorDataService;
    this.socialService = socialService;
    this.mapper = mapper;
  }

  public UserDetails findUserDetailsByEmail(String email) {
    LOGGER.info("Finding user details by email");
    return repository.findUserDetailsByEmail(email);
  }

  public UserDTO findById(UUID id) {
    LOGGER.info("Finding user by id");
    return mapper.map(
        repository
            .findById(id)
            .orElseThrow(
                () -> new ResourceNotFoundException("Nenhum usuário encontrado para este id")),
        UserDTO.class);
  }

  public UserEntity findEntityById(UUID id) {
    LOGGER.info("Finding user entity by id");
    return repository
        .findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Nenhum usuário encontrado para este id"));
  }

  public String findEmailById(UUID id) {
    LOGGER.info("Finding user's email by id");
    return repository
        .findEmailById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Nenhum usuário encontrado para este id"));
  }

  public UserDTO findIdByEmail(String email) {
    LOGGER.info("Finding user's id by email");
    return mapper.map(
        repository
            .findByEmail(email)
            .orElseThrow(
                () -> new ResourceNotFoundException("Nenhum usuário encontrado para este email")),
        UserDTO.class);
  }

  public Role findRoleById(UUID id) {
    LOGGER.info("Finding user's role by id");
    return repository
        .findRoleById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Nenhum usuário encontrado para este id"));
  }

  public boolean findActiveByEmail(String email) {
    LOGGER.info("Finding user's activity by email");
    return repository
        .findActiveByEmail(email)
        .orElseThrow(
            () -> new ResourceNotFoundException("Nenhum usuário encontrado para este email"));
  }

  private UserEntity entityFactory(RegisterRequest data) {
    String passwordEncoded = new BCryptPasswordEncoder().encode(data.getPassword().trim());

    return UserEntity.builder()
        .name(data.getName().trim())
        .email(data.getEmail().trim())
        .password(passwordEncoded)
        .role(data.getRole())
        .active(false)
        .createdAt(data.getCreatedAt())
        .build();
  }

  @Transactional(rollbackFor = Exception.class)
  public GenericResponseDTO<UUID> register(RegisterRequest data) {
    LOGGER.info("Creating a new user");
    validatorService.checkEmail(data.getEmail().trim());

    UserEntity entity = entityFactory(data);
    UUID userId = repository.save(entity).getId();

    return new GenericResponseDTO<>(userId, "Usuário criado com sucesso", LocalDateTime.now());
  }

  @Transactional(rollbackFor = Exception.class)
  public GenericResponseDTO<UUID> activateUser(UUID userId) {
    LOGGER.info("Activating user");

    UserEntity entity = findEntityById(userId);
    if (entity.getActive()) throw new UserAlreadyIsActive("Usuário já está ativo");
    entity.setActive(true);
    repository.save(entity);

    return new GenericResponseDTO<>(userId, "Usuário validado com sucesso", LocalDateTime.now());
  }

  public UserDTO findMe() {
    UUID id = tokenService.getUserId();
    return mapper.map(
        repository
            .findDataById(id)
            .orElseThrow(
                () -> new ResourceNotFoundException("Não foi possível encontrar seus dados")),
        UserDTO.class);
  }

  public UserDTO findProfileById(UUID id) {
    UserDTO user = findById(id);

    if (!user.getActive()) throw new InaccessibleResource("Este perfil está atualmente inativo");

    if (user.getRole().equals(Role.USER))
      throw new InaccessibleResource("Este usuário não possui perfil de professor");

    UserDTO response =
        mapper.map(
            repository
                .findProfileById(id)
                .orElseThrow(
                    () -> new ResourceNotFoundException("Nenhum perfil encontrado para este id")),
            UserDTO.class);

    if (response.getProfessorData() == null)
      throw new InaccessibleResource("Este usuário não possui perfil de professor");

    return response;
  }

  // change password
  @Transactional(rollbackFor = Exception.class)
  public GenericResponseDTO<UUID> changePassword(NewPasswordRequest data) {
    LOGGER.info("Changing user password");

    UUID userId = tokenService.getUserId();
    UserEntity user = findEntityById(userId);
    String newPassword = new BCryptPasswordEncoder().encode(data.getNewPassword().trim());
    user.setPassword(newPassword);
    UUID id = repository.save(user).getId();
    return new GenericResponseDTO<>(id, "Senha alterada com sucesso", LocalDateTime.now());
  }

  // reset password
  @Transactional(rollbackFor = Exception.class)
  public GenericResponseDTO<UUID> resetPassword(UUID userId, NewPasswordRequest data) {
    LOGGER.info("Resetting user password");

    UserEntity user = findEntityById(userId);
    String newPassword = new BCryptPasswordEncoder().encode(data.getNewPassword().trim());
    user.setPassword(newPassword);
    UUID id = repository.save(user).getId();
    return new GenericResponseDTO<>(id, "Senha redefinida com sucesso", LocalDateTime.now());
  }

  @Transactional(rollbackFor = Exception.class)
  private void updateUserRole(UUID userId, Role role) {
    UserEntity entity = findEntityById(userId);
    entity.setRole(role);
    repository.save(entity);
  }

  public GenericResponseDTO<UUID> createProfessor(UserDTO user, RegisterProfessorDTO data) {
    LOGGER.info("Changing user type to 'professor'");

    validatorService.checkIfUserExists(user.getId());
    validatorService.checkIfUserAlreadyHasProfessorRegistration(user.getId());

    professorDataService.create(user, data.getCreatedAt(), data.getProfessorData());
    if (!data.getSocials().isEmpty()) {
      socialService.createAll(user, data.getSocials());
    }

    if (findRoleById(user.getId()).equals(Role.USER)) {
      updateUserRole(user.getId(), Role.PROFESSOR);
    }

    return new GenericResponseDTO<>(
        user.getId(), "Usuário cadastrado como professor", LocalDateTime.now());
  }
}
