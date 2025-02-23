package br.com.tecflix_app.exception.auth;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class JwtCreationTokenException extends RuntimeException {
    public JwtCreationTokenException(String message) {
        super(message);
    }
}
