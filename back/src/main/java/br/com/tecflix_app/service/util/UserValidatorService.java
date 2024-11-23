package br.com.tecflix_app.service.util;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tecflix_app.exception.general.RepeatedDataException;
import br.com.tecflix_app.repository.UserRepository;

@Service
public class UserValidatorService {
    private final Logger LOGGER = Logger.getLogger(UserValidatorService.class.getName());
    private final UserRepository repository;

    @Autowired
    public UserValidatorService(UserRepository repository) {this.repository = repository;}

    public void checkEmail(String email) {
        LOGGER.info("Checking if email is already in use");
        if(repository.findIdByEmail(email).isPresent()) 
            throw new RepeatedDataException("Esse email já está em uso");
    
    }
}
