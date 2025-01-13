package br.com.tecflix_app.service;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.tecflix_app.data.DTO.v1.auth.NewPasswordDTO;
import br.com.tecflix_app.data.DTO.v1.auth.RegisterDTO;
import br.com.tecflix_app.data.DTO.v1.create.RegisterProfessorDTO;
import br.com.tecflix_app.data.DTO.v1.response.GenericResponseDTO;
import br.com.tecflix_app.data.DTO.v1.response.UserDTO;
import br.com.tecflix_app.exception.auth.UserAlreadyIsActive;
import br.com.tecflix_app.exception.general.ResourceNotFoundException;
import br.com.tecflix_app.mapper.contract.IMapperService;
import br.com.tecflix_app.model.User;
import br.com.tecflix_app.model.enums.Role;
import br.com.tecflix_app.repository.UserRepository;
import br.com.tecflix_app.service.auth.jwt.TokenService;
import br.com.tecflix_app.service.util.UserValidatorService;

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
                repository.findById(id).orElseThrow(
                        () -> new ResourceNotFoundException("Nenhum usuário encontrado para este id")),
                UserDTO.class);
    }

    public User findEntityById(UUID id) {
        LOGGER.info("Finding user entity by id");
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum usuário encontrado para este id"));
    }

    public String findEmailById(UUID id) {
        LOGGER.info("Finding user's email by id");
        return repository.findEmailById(id).orElseThrow(
                () -> new ResourceNotFoundException("Nenhum usuário encontrado para este id"));
    }

    public Role findRoleById(UUID id) {
        LOGGER.info("Finding user's role by id");
        return repository.findRoleById(id).orElseThrow(
                () -> new ResourceNotFoundException("Nenhum usuário encontrado para este id"));
    }

    public boolean findActiveByEmail(String email) {
        LOGGER.info("Finding user's activity by email");
        return repository.findActiveByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Nenhum usuário encontrado para este email"));
    }

    private User entityFactory(RegisterDTO data) {
        String passwordEncoded = new BCryptPasswordEncoder().encode(data.getPassword().trim());

        return User.builder()
                .name(data.getName().trim())
                .email(data.getEmail().trim())
                .password(passwordEncoded)
                .role(data.getRole())
                .active(false)
                .createdAt(data.getCreatedAt())
                .build();
    }

    @Transactional(rollbackFor = Exception.class)
    public GenericResponseDTO<UUID> register(RegisterDTO data) {
        LOGGER.info("Creating a new user");
        validatorService.checkEmail(data.getEmail().trim());

        User entity = entityFactory(data);
        UUID userId = repository.save(entity).getId();

        return new GenericResponseDTO<>(
                userId,
                "Usuário criado com sucesso",
                LocalDateTime.now());
    }

    public void activateUser(UUID userId) {
        LOGGER.info("Activating user");
        User entity = findEntityById(userId);
        if (entity.getActive())
            throw new UserAlreadyIsActive("Usuário já está ativo");
        entity.setActive(true);
        repository.save(entity);
    }

    public UserDTO findMe() {
        UUID id = tokenService.getUserId();
        return mapper.map(
                repository.findDataById(id).orElseThrow(
                        () -> new ResourceNotFoundException("Não foi possível encontrar seus dados")),
                UserDTO.class);
    }

    @Transactional(rollbackFor = Exception.class)
    public GenericResponseDTO<UUID> changePassword(NewPasswordDTO data) {
        LOGGER.info("Changing password");
        UUID userId = tokenService.getUserId();
        User user = findEntityById(userId);
        String newPassword = new BCryptPasswordEncoder().encode(data.getNewPassword().trim());
        user.setPassword(newPassword);
        UUID id = repository.save(user).getId();
        return new GenericResponseDTO<>(
                id,
                "Senha alterada com sucesso",
                LocalDateTime.now());
    }

    @Transactional(rollbackFor = Exception.class)
    private void updateUserRole(UUID userId, Role role) {
        User entity = findEntityById(userId);
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
                user.getId(),
                "Usuário cadastrado como professor",
                LocalDateTime.now());
    }
}