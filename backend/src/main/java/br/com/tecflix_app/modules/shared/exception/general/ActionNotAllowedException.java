package br.com.tecflix_app.modules.shared.exception.general;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ActionNotAllowedException extends RuntimeException {
  public ActionNotAllowedException(String message) {
    super(message);
  }
}
