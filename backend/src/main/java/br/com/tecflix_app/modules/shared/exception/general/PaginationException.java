package br.com.tecflix_app.modules.shared.exception.general;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PaginationException extends RuntimeException {
  public PaginationException(String message) {
    super(message);
  }
}
