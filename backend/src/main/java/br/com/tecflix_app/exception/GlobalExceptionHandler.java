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
import br.com.tecflix_app.exception.auth.UserAlreadyIsActive;
import br.com.tecflix_app.exception.auth.WrongPasswordException;
import br.com.tecflix_app.exception.email.EmailSendingException;
import br.com.tecflix_app.exception.general.ActionNotAllowedException;
import br.com.tecflix_app.exception.general.InaccessibleResource;
import br.com.tecflix_app.exception.general.RepeatedDataException;
import br.com.tecflix_app.exception.general.ResourceNotFoundException;
import br.com.tecflix_app.exception.payment.EVPGenerationException;
import br.com.tecflix_app.exception.payment.PixGenerationException;
import br.com.tecflix_app.exception.payment.QRCodeGenerationException;

@RestController
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    /*
     * Validators Exceptions
     */

    @SuppressWarnings("null")
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
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
            WebRequest request) {
        ExceptionResponse response = ExceptionResponse.builder()
                .timestamp(LocalDateTime.now())
                .title(exception.getMessage())
                .details(request.getDescription(false))
                .build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleResourceNotFoundExceptions(
            Exception exception,
            WebRequest request) {
        ExceptionResponse response = ExceptionResponse.builder()
                .timestamp(LocalDateTime.now())
                .title(exception.getMessage())
                .details(request.getDescription(false))
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RepeatedDataException.class)
    public final ResponseEntity<ExceptionResponse> handleRepeatedDataExceptions(
            Exception exception,
            WebRequest request) {
        ExceptionResponse response = ExceptionResponse.builder()
                .timestamp(LocalDateTime.now())
                .title(exception.getMessage())
                .details(request.getDescription(false))
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ActionNotAllowedException.class)
    public final ResponseEntity<ExceptionResponse> handleActionNotAllowedExceptions(
            Exception exception,
            WebRequest request) {
        ExceptionResponse response = ExceptionResponse.builder()
                .timestamp(LocalDateTime.now())
                .title(exception.getMessage())
                .details(request.getDescription(false))
                .build();
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(InaccessibleResource.class)
    public final ResponseEntity<ExceptionResponse> handleInaccessibleResourceExceptions(
            Exception exception,
            WebRequest request) {
        ExceptionResponse response = ExceptionResponse.builder()
                .timestamp(LocalDateTime.now())
                .title(exception.getMessage())
                .details(request.getDescription(false))
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /*
     * Authentication Exceptions
     */

    @ExceptionHandler(InvalidApiKeyException.class)
    public final ResponseEntity<ExceptionResponse> handleInvalidApiKeyExceptions(
            Exception exception,
            WebRequest request) {
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
            WebRequest request) {
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
            WebRequest request) {
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
            WebRequest request) {
        ExceptionResponse response = ExceptionResponse.builder()
                .timestamp(LocalDateTime.now())
                .title(exception.getMessage())
                .details(request.getDescription(false))
                .build();
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(UserAlreadyIsActive.class)
    public final ResponseEntity<ExceptionResponse> handleUserAlreadyIsActives(
            Exception exception,
            WebRequest request) {
        ExceptionResponse response = ExceptionResponse.builder()
                .timestamp(LocalDateTime.now())
                .title(exception.getMessage())
                .details(request.getDescription(false))
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WrongPasswordException.class)
    public final ResponseEntity<ExceptionResponse> handleWrongPasswordExceptions(
            Exception exception,
            WebRequest request) {
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
            WebRequest request) {
        ExceptionResponse response = ExceptionResponse.builder()
                .timestamp(LocalDateTime.now())
                .title(exception.getMessage())
                .details(request.getDescription(false))
                .build();
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    /*
     * Email Exceptions
     */

    @ExceptionHandler(EmailSendingException.class)
    public final ResponseEntity<ExceptionResponse> handleEmailSendingExceptions(
            Exception exception,
            WebRequest request) {
        ExceptionResponse response = ExceptionResponse.builder()
                .timestamp(LocalDateTime.now())
                .title(exception.getMessage())
                .details(request.getDescription(false))
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /*
     * Payment Exceptions
     */

    @ExceptionHandler(EVPGenerationException.class)
    public final ResponseEntity<ExceptionResponse> handleEVPGenerationExceptions(
            Exception exception,
            WebRequest request) {
        ExceptionResponse response = ExceptionResponse.builder()
                .timestamp(LocalDateTime.now())
                .title(exception.getMessage())
                .details(request.getDescription(false))
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PixGenerationException.class)
    public final ResponseEntity<ExceptionResponse> handlePixGenerationExceptions(
            Exception exception,
            WebRequest request) {
        ExceptionResponse response = ExceptionResponse.builder()
                .timestamp(LocalDateTime.now())
                .title(exception.getMessage())
                .details(request.getDescription(false))
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(QRCodeGenerationException.class)
    public final ResponseEntity<ExceptionResponse> handleQRCodeGenerationExceptions(
            Exception exception,
            WebRequest request) {
        ExceptionResponse response = ExceptionResponse.builder()
                .timestamp(LocalDateTime.now())
                .title(exception.getMessage())
                .details(request.getDescription(false))
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
