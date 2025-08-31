package br.com.tecflix_app.modules.module.infra.presentation.dtos.v1;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateModuleDTO {
  @NotNull(message = "O título do módulo é obrigatório")
  @NotBlank(message = "O título do módulo é obrigatório")
  @Size(min = 5, max = 40, message = "O título do módulo deve ter entre 5 e 40 caracteres")
  private String title;

  @NotNull(message = "O id do curso é obrigatório")
  private UUID courseId;
}
