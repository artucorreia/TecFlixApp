package br.com.tecflix_app.service;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.tecflix_app.data.DTO.v1.auth.RegisterDTO;
import br.com.tecflix_app.data.DTO.v1.create.RegisterProfessorDTO;
import br.com.tecflix_app.data.DTO.v1.create.CreateSocialDTO;
import br.com.tecflix_app.data.DTO.v1.response.CreateResponseDTO;
import br.com.tecflix_app.data.DTO.v1.response.UserDTO;
import br.com.tecflix_app.exception.general.ResourceNotFoundException;
import br.com.tecflix_app.mapper.Mapper;
import br.com.tecflix_app.model.User;
import br.com.tecflix_app.model.enums.Role;
import br.com.tecflix_app.repository.UserRepository;
import br.com.tecflix_app.service.util.UserValidatorService;

@Service
public class UserService {
    private final Logger LOGGER = Logger.getLogger(UserService.class.getName());    

    private final UserRepository repository;
    private final UserValidatorService validatorService;
    private final ProfessorDataService professorDataService;
    private final AddressService addressService;
    private final BankDataService bankDataService;
    private final SocialService socialService;
    private final Mapper mapper;

    @Autowired
    public UserService(
        UserRepository repository,
        UserValidatorService validatorService,
        ProfessorDataService professorDataService,
        AddressService addressService,
        BankDataService bankDataService,
        SocialService socialService,
        Mapper mapper
    ) {
        this.repository = repository;
        this.validatorService = validatorService;
        this.professorDataService = professorDataService;
        this.addressService = addressService;
        this.bankDataService = bankDataService;
        this.socialService = socialService;
        this.mapper = mapper;
    }
    
    public UserDetails findUserDetailsByEmail(String email) { 
        LOGGER.info("Finding user details by email");
        return repository.findUserDetailsByEmail(email);
    }
    
    public UserDTO findById(UUID id) {
        return mapper.map(
            repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Nenhum usuário encontrado para este id")
            ),
    UserDTO.class
        );
    }
    
    public String findEmailById(UUID id) { 
        LOGGER.info("Finding email by id");
        return repository.findEmailById(id).orElseThrow(
            () -> new ResourceNotFoundException("Nenhum usuário encontrado para este id")
        );
    }
    
    public Role findRoleById(UUID id) { 
        LOGGER.info("Finding email by id");
        return repository.findRoleById(id).orElseThrow(
            () -> new ResourceNotFoundException("Nenhum usuário encontrado para este id")
        );
    }
    
    public boolean findActiveByEmail(String email) {
        LOGGER.info("Finding active by email");
        return repository.findActiveByEmail(email).orElseThrow(
            () -> new ResourceNotFoundException("Nenhum usuário encontrado para este email")
        ); 
    }

    private User entityFactory(RegisterDTO data) {
        String passwordEncoded = new BCryptPasswordEncoder().encode(data.getPassword().trim());

        return User.builder()
        .name(data.getName().trim())
        .email(data.getEmail().trim())
        .password(passwordEncoded)
        .role(data.getRole())
        .active(data.getActive())
        .createdAt(data.getCreatedAt())
        .build();
    }

    @Transactional(rollbackFor = Exception.class)
    public CreateResponseDTO<UUID> register(RegisterDTO data) {
        LOGGER.info("Creating a new user");
        validatorService.checkEmail(data.getEmail().trim());
        
        User entity = entityFactory(data);        
        UUID userId = repository.save(entity).getId();
        
        return new CreateResponseDTO<>(
            userId, 
            "user created successfuly",
            LocalDateTime.now()
            );
        }
        
    @Transactional(rollbackFor = Exception.class)
    public CreateResponseDTO<UUID> createProfessor(UserDTO user, RegisterProfessorDTO data) {
        LOGGER.info("Changing user type to 'professor'");
        
        validatorService.checkIfUserExists(user.getId());
        validatorService.checkIfUserAlreadyHasProfessorRegistration(user.getId());
        
        professorDataService.create(user, data.getCreatedAt(), data.getProfessorData());
        addressService.create(user, data.getAddress());
        bankDataService.create(user, data.getBankData());
        if (!data.getSocials().isEmpty()) {
            for (CreateSocialDTO social : data.getSocials()) {
                socialService.create(user, social);
            }
        }
        
        if (
            findRoleById(user.getId()).equals(Role.USER)
        ) {
            User entity = mapper.map(user, User.class);
            entity.setRole(Role.PROFESSOR);
            repository.save(entity);
        }
        
        return new CreateResponseDTO<>(
            user.getId(), 
            "user is a professor",
            LocalDateTime.now()
        );
    }
}