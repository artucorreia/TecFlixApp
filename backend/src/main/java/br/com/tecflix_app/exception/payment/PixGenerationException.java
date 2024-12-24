package br.com.tecflix_app.exception.payment;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PixGenerationException extends RuntimeException{
    public PixGenerationException(String message) {
        super(message);
    }
}
