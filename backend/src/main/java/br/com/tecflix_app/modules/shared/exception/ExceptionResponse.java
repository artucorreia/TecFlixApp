package br.com.tecflix_app.modules.shared.exception;

import java.time.LocalDateTime;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ExceptionResponse {
  private Boolean success;
  private String message;
  private String uri;
  private int code;
  private LocalDateTime timestamp;
}
