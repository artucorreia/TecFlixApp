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
public class RefreshTokenDTO {
  @NotNull(message = "O campo 'token' é obrigatório")
  @NotBlank(message = "O campo 'token' não pode estar em branco")
  @Size(min = 36, max = 36, message = "O campo 'token' deve ter 36 caracteres")
  private String token;
}
