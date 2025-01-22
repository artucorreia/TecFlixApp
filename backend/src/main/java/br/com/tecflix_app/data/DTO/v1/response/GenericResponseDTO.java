package br.com.tecflix_app.data.DTO.v1.response;

import java.time.LocalDateTime;

public class GenericResponseDTO<T> {
  private T id;
  private String message;
  private LocalDateTime timestamp;

  public GenericResponseDTO() {}

  public GenericResponseDTO(T id, String message, LocalDateTime timestamp) {
    this.id = id;
    this.message = message;
    this.timestamp = timestamp;
  }

  public T getId() {
    return id;
  }

  public void setId(T id) {
    this.id = id;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
  }
}
