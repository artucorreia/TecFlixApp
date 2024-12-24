package br.com.tecflix_app.exception.general;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RepeatedDataException extends RuntimeException{
    public RepeatedDataException(String message) {
        super(message);
     }
}
