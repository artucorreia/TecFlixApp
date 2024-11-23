package br.com.tecflix_app.data.DTO.v1.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class CreateResponseDTO <T> {
    private T id; 
    private String message;
    private LocalDateTime timestamp;
}