package br.com.tecflix_app.data.DTO.v1.auth;

import java.time.LocalDateTime;

import br.com.tecflix_app.data.DTO.v1.response.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EmailCodeDTO {
    private String code;
    private UserDTO user;
    private LocalDateTime createdAt;
}
