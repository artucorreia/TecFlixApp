package br.com.tecflix_app.exception.auth;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserAlreadyIsActive extends RuntimeException {
    public UserAlreadyIsActive(String message) {
        super(message);
    }
}
