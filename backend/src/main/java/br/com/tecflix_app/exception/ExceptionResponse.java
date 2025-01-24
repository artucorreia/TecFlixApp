package br.com.tecflix_app.exception;

import java.time.LocalDateTime;

public class ExceptionResponse {
  private LocalDateTime timestamp;
  private String title;
  private String details;

  public ExceptionResponse(LocalDateTime timestamp, String title, String details) {
    this.timestamp = timestamp;
    this.title = title;
    this.details = details;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDetails() {
    return details;
  }

  public void setDetails(String details) {
    this.details = details;
  }
}
