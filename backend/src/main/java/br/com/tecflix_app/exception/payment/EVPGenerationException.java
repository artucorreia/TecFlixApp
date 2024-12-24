package br.com.tecflix_app.exception.payment;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EVPGenerationException extends RuntimeException{
    public EVPGenerationException(String message) {
        super(message);
    }
}
