package br.com.tecflix_app.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class ExceptionResponse {
    private LocalDateTime timestamp;
    private String title;
    private String details;
}
