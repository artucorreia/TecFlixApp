package br.com.tecflix_app.modules.shared.exception;

import br.com.tecflix_app.modules.auth.domain.exception.InvalidApiKeyException;
import br.com.tecflix_app.modules.auth.domain.exception.InvalidTokenException;
import br.com.tecflix_app.modules.auth.domain.exception.JwtCreationTokenException;
import br.com.tecflix_app.modules.shared.exception.auth.InactiveUserException;
import br.com.tecflix_app.modules.shared.exception.auth.RefreshTokenException;
import br.com.tecflix_app.modules.shared.exception.auth.UserAlreadyIsActive;
import br.com.tecflix_app.modules.shared.exception.auth.WrongPasswordException;
import br.com.tecflix_app.modules.shared.exception.email.EmailSendingException;
import br.com.tecflix_app.modules.shared.exception.general.ActionNotAllowedException;
import br.com.tecflix_app.modules.shared.exception.general.InaccessibleResource;
import br.com.tecflix_app.modules.shared.exception.general.RepeatedDataException;
import br.com.tecflix_app.modules.shared.exception.general.ResourceNotFoundException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
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
    Map<String, String> errors = new HashMap<>();
    for (var exception : ex.getBindingResult().getFieldErrors()) {
      errors.put(exception.getField(), exception.getDefaultMessage());
    }

    MethodArgumentNotValidExceptionResponse response =
        MethodArgumentNotValidExceptionResponse.builder()
            .success(false)
            .message("Erro na validação dos campos")
            .uri(request.getDescription(false))
            .code(HttpStatus.BAD_REQUEST.value())
            .fields(errors)
            .timestamp(LocalDateTime.now())
            .build();

    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  /*
   * General Exceptions
   */

  @ExceptionHandler(Exception.class)
  public final ResponseEntity<ExceptionResponse> handleAllExceptions(
      Exception exception, WebRequest request) {
    ExceptionResponse response =
        ExceptionResponse.builder()
            .success(false)
            .message(exception.getMessage())
            .uri(request.getDescription(false))
            .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .timestamp(LocalDateTime.now())
            .build();
    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public final ResponseEntity<ExceptionResponse> handleResourceNotFoundExceptions(
      Exception exception, WebRequest request) {
    ExceptionResponse response =
        ExceptionResponse.builder()
            .success(false)
            .message(exception.getMessage())
            .uri(request.getDescription(false))
            .code(HttpStatus.NOT_FOUND.value())
            .timestamp(LocalDateTime.now())
            .build();
    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(RepeatedDataException.class)
  public final ResponseEntity<ExceptionResponse> handleRepeatedDataExceptions(
      Exception exception, WebRequest request) {
    ExceptionResponse response =
        ExceptionResponse.builder()
            .success(false)
            .message(exception.getMessage())
            .uri(request.getDescription(false))
            .code(HttpStatus.BAD_REQUEST.value())
            .timestamp(LocalDateTime.now())
            .build();
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ActionNotAllowedException.class)
  public final ResponseEntity<ExceptionResponse> handleActionNotAllowedExceptions(
      Exception exception, WebRequest request) {
    ExceptionResponse response =
        ExceptionResponse.builder()
            .success(false)
            .message(exception.getMessage())
            .uri(request.getDescription(false))
            .code(HttpStatus.FORBIDDEN.value())
            .timestamp(LocalDateTime.now())
            .build();
    return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler(InaccessibleResource.class)
  public final ResponseEntity<ExceptionResponse> handleInaccessibleResourceExceptions(
      Exception exception, WebRequest request) {
    ExceptionResponse response =
        ExceptionResponse.builder()
            .success(false)
            .message(exception.getMessage())
            .uri(request.getDescription(false))
            .code(HttpStatus.BAD_REQUEST.value())
            .timestamp(LocalDateTime.now())
            .build();
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  /*
   * Authentication Exceptions
   */

  @ExceptionHandler(InvalidApiKeyException.class)
  public final ResponseEntity<ExceptionResponse> handleInvalidApiKeyExceptions(
      Exception exception, WebRequest request) {
    ExceptionResponse response =
        ExceptionResponse.builder()
            .success(false)
            .message(exception.getMessage())
            .uri(request.getDescription(false))
            .code(HttpStatus.UNAUTHORIZED.value())
            .timestamp(LocalDateTime.now())
            .build();
    return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(JwtCreationTokenException.class)
  public final ResponseEntity<ExceptionResponse> handleJwtCreationTokenExceptions(
      Exception exception, WebRequest request) {
    ExceptionResponse response =
        ExceptionResponse.builder()
            .success(false)
            .message(exception.getMessage())
            .uri(request.getDescription(false))
            .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .timestamp(LocalDateTime.now())
            .build();
    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(InvalidTokenException.class)
  public final ResponseEntity<ExceptionResponse> handleInvalidTokenExceptions(
      Exception exception, WebRequest request) {
    ExceptionResponse response =
        ExceptionResponse.builder()
            .success(false)
            .message(exception.getMessage())
            .uri(request.getDescription(false))
            .code(HttpStatus.UNAUTHORIZED.value())
            .timestamp(LocalDateTime.now())
            .build();
    return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(InactiveUserException.class)
  public final ResponseEntity<ExceptionResponse> handleInactiveUserExceptions(
      Exception exception, WebRequest request) {
    ExceptionResponse response =
        ExceptionResponse.builder()
            .success(false)
            .message(exception.getMessage())
            .uri(request.getDescription(false))
            .code(HttpStatus.FORBIDDEN.value())
            .timestamp(LocalDateTime.now())
            .build();
    return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler(UserAlreadyIsActive.class)
  public final ResponseEntity<ExceptionResponse> handleUserAlreadyIsActives(
      Exception exception, WebRequest request) {
    ExceptionResponse response =
        ExceptionResponse.builder()
            .success(false)
            .message(exception.getMessage())
            .uri(request.getDescription(false))
            .code(HttpStatus.BAD_REQUEST.value())
            .timestamp(LocalDateTime.now())
            .build();
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(WrongPasswordException.class)
  public final ResponseEntity<ExceptionResponse> handleWrongPasswordExceptions(
      Exception exception, WebRequest request) {
    ExceptionResponse response =
        ExceptionResponse.builder()
            .success(false)
            .message(exception.getMessage())
            .uri(request.getDescription(false))
            .code(HttpStatus.BAD_REQUEST.value())
            .timestamp(LocalDateTime.now())
            .build();
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(RefreshTokenException.class)
  public final ResponseEntity<ExceptionResponse> handleRefreshTokenExceptions(
      Exception exception, WebRequest request) {
    ExceptionResponse response =
        ExceptionResponse.builder()
            .success(false)
            .message(exception.getMessage())
            .uri(request.getDescription(false))
            .code(HttpStatus.UNAUTHORIZED.value())
            .timestamp(LocalDateTime.now())
            .build();
    return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
  }

  /*
   * Email Exceptions
   */

  @ExceptionHandler(EmailSendingException.class)
  public final ResponseEntity<ExceptionResponse> handleEmailSendingExceptions(
      Exception exception, WebRequest request) {
    ExceptionResponse response =
        ExceptionResponse.builder()
            .success(false)
            .message(exception.getMessage())
            .uri(request.getDescription(false))
            .code(HttpStatus.BAD_REQUEST.value())
            .timestamp(LocalDateTime.now())
            .build();
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }
}
