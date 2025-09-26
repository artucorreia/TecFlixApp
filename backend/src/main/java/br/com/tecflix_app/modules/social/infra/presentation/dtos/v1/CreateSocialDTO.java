package br.com.tecflix_app.modules.social.infra.presentation.dtos.v1;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateSocialDTO {
  @NotNull(message = "O campo 'socialNameId' é obrigatório")
  @Positive(message = "O campo 'socialNameId' deve ser um número válido")
  private Long socialNameId;

  @NotNull(message = "O campo 'url' é obrigatório")
  @NotBlank(message = "O campo 'url' não pode estar em branco")
  @Size(min = 10, max = 255, message = "O campo 'url' deve ter entre 10 e 255 caracteres")
  private String url;
}
