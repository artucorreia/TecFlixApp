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
  @NotNull(message = "O campo 'title' é obrigatório")
  @NotBlank(message = "O campo 'title' não pode estar em branco")
  @Size(min = 5, max = 20, message = "O campo 'title' deve ter entre 5 e 20 caracteres")
  private String title;

  @NotNull(message = "O campo 'videoPath' é obrigatório")
  @NotBlank(message = "O campo 'videoPath' não pode estar em branco")
  @Size(max = 255, message = "O campo 'videoPath' deve ter no máximo 255 caracteres")
  private String videoPath;

  @NotNull(message = "O id do módulo é obrigatório")
  private Long moduleId;
}
