package br.com.tecflix_app.modules.shared.exception;

import java.time.LocalDateTime;
import java.util.Map;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MethodArgumentNotValidExceptionResponse {
  private Boolean success;
  private String message;
  private String uri;
  private int code;
  private Map<String, String> fields;
  private LocalDateTime timestamp;
}
