package br.com.tecflix_app.exception;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.tecflix_app.exception.auth.InactiveUserException;
import br.com.tecflix_app.exception.auth.InvalidApiKeyException;
import br.com.tecflix_app.exception.auth.InvalidTokenException;
import br.com.tecflix_app.exception.auth.JwtCreationTokenException;
import br.com.tecflix_app.exception.auth.RefreshTokenException;
import br.com.tecflix_app.exception.auth.WrongPasswordException;

@RestController
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
    /* 
     * Validators Exceptions 
    */

    @SuppressWarnings("null")
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        List<String> errorMessages = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();

        ExceptionResponse response = ExceptionResponse.builder()
            .timestamp(LocalDateTime.now())
            .title(String.join(", ", errorMessages))
            .details(request.getDescription(false))
            .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /*
     * General Exceptions 
    */

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(
        Exception exception,
        WebRequest request
    ) {
        ExceptionResponse response = ExceptionResponse.builder()
            .timestamp(LocalDateTime.now())
            .title(exception.getMessage())
            .details(request.getDescription(false))
            .build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /* 
     * Authentication Exceptions
    */

    @ExceptionHandler(InvalidApiKeyException.class)
    public final ResponseEntity<ExceptionResponse> handleInvalidApiKeyExceptions(
        Exception exception,
        WebRequest request
    ) {
        ExceptionResponse response = ExceptionResponse.builder()
            .timestamp(LocalDateTime.now())
            .title(exception.getMessage())
            .details(request.getDescription(false))
            .build();
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
    
    @ExceptionHandler(JwtCreationTokenException.class)
    public final ResponseEntity<ExceptionResponse> handleJwtCreationTokenExceptions(
        Exception exception,
        WebRequest request
    ) {
        ExceptionResponse response = ExceptionResponse.builder()
            .timestamp(LocalDateTime.now())
            .title(exception.getMessage())
            .details(request.getDescription(false))
            .build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidTokenException.class)
    public final ResponseEntity<ExceptionResponse> handleInvalidTokenExceptions(
        Exception exception,
        WebRequest request
    ) {
        ExceptionResponse response = ExceptionResponse.builder()
            .timestamp(LocalDateTime.now())
            .title(exception.getMessage())
            .details(request.getDescription(false))
            .build();
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InactiveUserException.class)
    public final ResponseEntity<ExceptionResponse> handleInactiveUserExceptions(
        Exception exception,
        WebRequest request
    ) {
        ExceptionResponse response = ExceptionResponse.builder()
            .timestamp(LocalDateTime.now())
            .title(exception.getMessage())
            .details(request.getDescription(false))
            .build();
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }
    
    @ExceptionHandler(WrongPasswordException.class)
    public final ResponseEntity<ExceptionResponse> handleWrongPasswordExceptions(
        Exception exception,
        WebRequest request
    ) {
        ExceptionResponse response = ExceptionResponse.builder()
            .timestamp(LocalDateTime.now())
            .title(exception.getMessage())
            .details(request.getDescription(false))
            .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RefreshTokenException.class)
    public final ResponseEntity<ExceptionResponse> handleRefreshTokenExceptions(
        Exception exception,
        WebRequest request
    ) {
        ExceptionResponse response = ExceptionResponse.builder()
            .timestamp(LocalDateTime.now())
            .title(exception.getMessage())
            .details(request.getDescription(false))
            .build();
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
}
