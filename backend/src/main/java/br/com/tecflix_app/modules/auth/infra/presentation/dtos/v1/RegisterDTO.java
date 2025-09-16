package br.com.tecflix_app.modules.auth.infra.presentation.dtos.v1;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RegisterDTO {
  @NotNull(message = "O campo 'name' é obrigatório")
  @NotBlank(message = "O campo 'name' não pode estar em branco")
  @Size(min = 3, max = 30, message = "O campo 'name' deve ter entre 3 e 30 caracteres")
  private String name;

  @NotNull(message = "O campo 'email' é obrigatório")
  @NotBlank(message = "O campo 'email' não pode estar em branco")
  @Email(message = "O campo 'email' deve conter um formato válido")
  @Size(max = 50, message = "O campo 'email' deve ter no máximo 50 caracteres")
  private String email;

  @NotNull(message = "O campo 'password' é obrigatório")
  @NotBlank(message = "O campo 'password' não pode estar em branco")
  @Size(min = 8, max = 50, message = "O campo 'password' deve ter entre 8 e 50 caracteres")
  private String password;
}
