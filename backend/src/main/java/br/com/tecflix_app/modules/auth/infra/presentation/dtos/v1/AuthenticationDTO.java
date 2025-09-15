package br.com.tecflix_app.modules.auth.infra.presentation.dtos.v1;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AuthenticationDTO {
  @Email private String email;

  @Size(min = 8, max = 50)
  private String password;
}
