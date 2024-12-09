package br.com.tecflix_app.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.tecflix_app.data.DTO.v1.response.GenericResponseDTO;
import br.com.tecflix_app.data.DTO.v1.response.UserDTO;
import br.com.tecflix_app.exception.auth.UserAlreadyIsActive;
import br.com.tecflix_app.exception.general.ResourceNotFoundException;
import br.com.tecflix_app.mapper.Mapper;
import br.com.tecflix_app.model.EmailCode;
import br.com.tecflix_app.model.User;
import br.com.tecflix_app.repository.EmailCodeRepository;
import br.com.tecflix_app.service.email.EmailSenderService;
import br.com.tecflix_app.service.util.EmailCodeGenerator;

@Service
public class EmailCodeService {
    
    private final EmailCodeRepository repository;
    private final UserService userService;
    private final EmailSenderService emailSenderService;
    private final Mapper mapper;

    @Autowired
    public EmailCodeService(
        EmailCodeRepository repository,
        UserService userService,
        EmailSenderService emailSenderService,
        Mapper mapper
    ) {
        this.repository = repository;
        this.userService = userService;
        this.emailSenderService = emailSenderService;
        this.mapper = mapper;
    }

    public EmailCode findByCode(String code) {
        return repository.findByCode(code).orElseThrow(() -> new ResourceNotFoundException("Código inválido ou expirado"));
    }

    @Transactional(rollbackFor = Exception.class)
    public GenericResponseDTO<Long> create(UUID userId) {
        UserDTO user = userService.findById(userId);
        
        if (user.getActive()) throw new UserAlreadyIsActive("Usuário já está ativo");

        deleteByUserId(userId);
        
        EmailCode entity = EmailCode.builder()
            .user(mapper.map(user, User.class))
            .code(EmailCodeGenerator.genarateCode())
            .createdAt(LocalDateTime.now())
            .build();

        EmailCode result = repository.save(entity);

        emailSenderService.sendEmailCode(
            user.getEmail(), 
            user.getName(), 
            result.getCode()
        );

        return new GenericResponseDTO<>(
            result.getId(), 
            "Código enviado com sucesso, verifique sua caixa de entrada",
            LocalDateTime.now()
        );
    }

    @Transactional(rollbackFor = Exception.class)
    public GenericResponseDTO<UUID> validate(String code) {
        UUID userId = findByCode(code).getUser().getId();
        userService.activateUser(userId);

        return new GenericResponseDTO<>(
            userId,
            "Usuário validado com sucesso",
            LocalDateTime.now()
        );
    }

    public void deleteByUserId(UUID userId) {
        repository.deleteByUserId(userId);
    }
}
