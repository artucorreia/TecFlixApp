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
import br.com.tecflix_app.data.DTO.v1.response.CreateResponseDTO;
import br.com.tecflix_app.model.User;
import br.com.tecflix_app.repository.UserRepository;
import br.com.tecflix_app.service.util.UserValidatorService;

@Service
public class UserService {
    private final Logger LOGGER = Logger.getLogger(UserService.class.getName());    

    private final UserRepository repository;
    private final UserValidatorService validatorService;

    @Autowired
    public UserService(
        UserRepository repository,
        UserValidatorService validatorService
    ) {
        this.repository = repository;
        this.validatorService = validatorService;
    }
    
    public UserDetails findUserDetailsByEmail(String email) { 
        LOGGER.info("Finding user details by email");
        return repository.findUserDetailsByEmail(email);
    }
    
    public String findEmailById(UUID id) { 
        LOGGER.info("Finding email by id");
        return repository.findEmailById(id);
    }
    
    public boolean findActiveByEmail(String email) {
        LOGGER.info("Finding active by email");
        return repository.findActiveByEmail(email); 
    }

    @Transactional(rollbackFor = Exception.class)
    public CreateResponseDTO<UUID> register(RegisterDTO data) {
        LOGGER.info("Creating a new user");
        validatorService.checkEmail(data.getEmail().trim());
        String passwordEncoded = new BCryptPasswordEncoder().encode(data.getPassword().trim());
        
        User entity = User.builder()
            .name(data.getName().trim())
            .email(data.getEmail().trim())
            .password(passwordEncoded)
            .role(data.getRole())
            .active(data.getActive())
            .createdAt(data.getCreatedAt())
            .build();

        UUID userId = repository.save(entity).getId();
        
        return new CreateResponseDTO<>(
            userId, 
            "user created successfuly",
            LocalDateTime.now()
        );
    }
}