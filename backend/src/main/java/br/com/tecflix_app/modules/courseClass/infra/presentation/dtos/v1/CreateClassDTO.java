package br.com.tecflix_app.modules.courseClass.infra.presentation.dtos.v1;

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
public class CreateClassDTO {
  @NotNull(message = "O título da aula é obrigatório")
  @NotBlank(message = "O título da aula é obrigatório")
  @Size(min = 5, max = 20, message = "O título da aula deve ter entre 5 e 20 caracteres")
  private String title;

  @NotNull(message = "O caminho do vídeo da aula é obrigatório")
  @NotBlank(message = "O caminho do vídeo da aula é obrigatório")
  @Size(max = 255, message = "O caminho do vídeo da aula deve ter no máximo 200 caracteres")
  private String videoPath;

  @NotNull(message = "O id do módulo é obrigatório")
  private Long moduleId;
}
