package br.com.tecflix_app.modules.emailCode.infra.presentation.dto.v1;

import java.time.LocalDateTime;

import br.com.tecflix_app.modules.user.infra.dtos.v1.UserDTO;
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
