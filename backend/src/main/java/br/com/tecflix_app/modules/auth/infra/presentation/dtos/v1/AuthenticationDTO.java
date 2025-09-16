package br.com.tecflix_app.modules.auth.infra.presentation.dtos.v1;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AuthenticationDTO {
  @NotNull(message = "O campo 'email' é obrigatório")
  @NotBlank(message = "O campo 'email' não pode estar em branco")
  @Email(message = "O campo 'email' deve possuir um formato válido")
  private String email;

  @NotNull(message = "O campo 'password' é obrigatório")
  @NotBlank(message = "O campo 'password' não pode estar em branco")
  @Size(min = 8, max = 50, message = "O campo 'password' deve ter entre 8 e 50 caracteres")
  private String password;
}
