package br.com.tecflix_app.service.util;

import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tecflix_app.exception.general.ActionNotAllowedException;
import br.com.tecflix_app.exception.general.RepeatedDataException;
import br.com.tecflix_app.exception.general.ResourceNotFoundException;
import br.com.tecflix_app.model.enums.Role;
import br.com.tecflix_app.repository.UserRepository;
import br.com.tecflix_app.service.ProfessorDataService;

@Service
public class UserValidatorService {
    private final Logger LOGGER = Logger.getLogger(UserValidatorService.class.getName());
    private final UserRepository repository;
    private final ProfessorDataService professorDataService;

    @Autowired
    public UserValidatorService(
        UserRepository repository,
        ProfessorDataService professorDataService
    ) {
        this.repository = repository;
        this.professorDataService = professorDataService;
    }

    public void checkEmail(String email) {
        LOGGER.info("Checking if email is already in use");
        if(repository.findIdByEmail(email).isPresent()) 
            throw new RepeatedDataException("Este email já está em uso");
    }

    public void checkIfUserExists(UUID userId) {
        repository.findEmailById(userId).orElseThrow(
            () -> new ResourceNotFoundException("Nenhum usuário encontrado para este id")
        );
    }

    public boolean isRoleNotAllowed(UUID userId, Role role) {
            return repository.findRoleById(userId).orElseThrow(
                        () -> new ResourceNotFoundException("Nenhum usuário encontrado para este id")
                    ).equals(role);
    }

    public void checkIfUserAlreadyHasProfessorRegistration(UUID userId) {
        if (professorDataService.findIdByUserId(userId).isPresent())
            throw new ActionNotAllowedException("O usuário já possui cadastro de professor");
    }
}
