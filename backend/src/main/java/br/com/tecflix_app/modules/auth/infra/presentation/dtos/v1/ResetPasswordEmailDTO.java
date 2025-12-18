package br.com.tecflix_app.modules.auth.infra.presentation.dtos.v1;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResetPasswordEmailDTO {
  @NotNull(message = "O campo 'email' é obrigatório")
  @NotBlank(message = "O campo 'email' não pode estar em branco")
  @Email(message = "O campo 'email' deve possuir um formato válido")
  private String email;
}
