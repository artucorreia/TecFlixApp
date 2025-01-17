package br.com.tecflix_app.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.tecflix_app.data.DTO.v1.auth.EmailCodeDTO;
import br.com.tecflix_app.data.DTO.v1.auth.NewPasswordDTO;
import br.com.tecflix_app.data.DTO.v1.response.GenericResponseDTO;
import br.com.tecflix_app.data.DTO.v1.response.UserDTO;
import br.com.tecflix_app.exception.auth.UserAlreadyIsActive;
import br.com.tecflix_app.exception.general.ResourceNotFoundException;
import br.com.tecflix_app.mapper.contract.IMapperService;
import br.com.tecflix_app.model.EmailCode;
import br.com.tecflix_app.repository.EmailCodeRepository;
import br.com.tecflix_app.service.email.EmailSenderService;
import br.com.tecflix_app.service.util.EmailCodeGenerator;

@Service
public class EmailCodeService {

    private final EmailCodeRepository repository;
    private final UserService userService;
    private final EmailSenderService emailSenderService;
    private final IMapperService mapper;

    @Autowired
    public EmailCodeService(
            EmailCodeRepository repository,
            UserService userService,
            EmailSenderService emailSenderService,
            IMapperService mapper) {
        this.repository = repository;
        this.userService = userService;
        this.emailSenderService = emailSenderService;
        this.mapper = mapper;
    }

    public EmailCode findByCodeAndUserId(String code, UUID userId) {
        return repository.findByCodeAndUserId(code, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Código inválido ou expirado"));
    }

    @Transactional(rollbackFor = Exception.class)
    public GenericResponseDTO<Long> create(UUID userId, boolean resetPassword) {
        UserDTO user = userService.findById(userId);

        if (user.getActive() && !resetPassword)
            throw new UserAlreadyIsActive("Usuário já está ativo");

        deleteByUserId(userId);

        EmailCodeDTO emailCode = EmailCodeDTO.builder()
                .user(user)
                .code(EmailCodeGenerator.genarateCode())
                .createdAt(LocalDateTime.now())
                .build();

        EmailCode result = repository.save(mapper.map(emailCode, EmailCode.class));

        if (!resetPassword) {
            sendEmailToValidateUser(userId, user.getEmail(), user.getName(), result.getCode());
        } else {
            sendEmailToResetPassword(userId, user.getEmail(), user.getName(), result.getCode());
        }

        return new GenericResponseDTO<>(
                result.getId(),
                "Código enviado com sucesso, verifique sua caixa de entrada",
                LocalDateTime.now());
    }

    // validate code and active user
    public GenericResponseDTO<UUID> validate(String code, UUID userId) {
        // check if the code exists
        findByCodeAndUserId(code, userId);
        return userService.activateUser(userId);
    }

    // validate code and reset password
    public GenericResponseDTO<UUID> validate(String code, UUID userId, NewPasswordDTO data) {
        // check if the code exists
        findByCodeAndUserId(code, userId);
        return userService.resetPassword(userId, data);
    }

    public void deleteByUserId(UUID userId) {
        repository.deleteByUserId(userId);
    }

    private void sendEmailToValidateUser(UUID userId, String email, String name, String code) {
        String path = "/sing-up/authenticate-code?code=" + code + "&userId=" + userId;
        emailSenderService.sendEmailCode(
                email,
                name,
                "Clique no botão abaixo para validar o seu e-mail:",
                "Validar E-mail",
                path,
                "Código de Validação:" + code);
    }

    private void sendEmailToResetPassword(UUID userId, String email, String name, String code) {
        String path = "/sing-in/reset-password?code=" + code + "&userId=" + userId;
        emailSenderService.sendEmailCode(
                email,
                name,
                "Clique no botão abaixo para resetar sua senha:",
                "Resetar Senha",
                path,
                "Acesse o link para resetar sua senha");
    }

}
