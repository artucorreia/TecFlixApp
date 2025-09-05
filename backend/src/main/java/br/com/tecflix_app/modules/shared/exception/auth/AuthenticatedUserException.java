package br.com.tecflix_app.modules.shared.exception.auth;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AuthenticatedUserException extends RuntimeException {
  public AuthenticatedUserException(String message) {
    super(message);
  }
}
