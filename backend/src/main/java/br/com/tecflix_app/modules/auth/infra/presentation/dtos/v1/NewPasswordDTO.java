package br.com.tecflix_app.modules.auth.infra.presentation.dtos.v1;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NewPasswordDTO {
  @NotNull(message = "O campo 'newPassword' é obrigatório")
  @NotBlank(message = "O campo 'newPassword' não pode estar em branco")
  @Size(min = 8, max = 50, message = "O campo 'newPassword' deve ter entre 8 e 50 caracteres")
  private String newPassword;
}
