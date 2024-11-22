package br.com.tecflix_app.exception.auth;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class RefreshTokenException extends RuntimeException {
    public RefreshTokenException(String massege) {
        super(massege);
    }
}
