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

import br.com.tecflix_app.exception.auth.InvalidApiKeyException;

@RestController
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
    
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
}
