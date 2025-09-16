package br.com.tecflix_app.modules.course.infra.presentation.dtos.v1;

import java.util.List;

import br.com.tecflix_app.modules.shared.dto.v1.IdDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
public class CreateCourseDTO {
  @NotNull(message = "O campo 'title' é obrigatório")
  @NotBlank(message = "O campo 'title' não pode estar em branco")
  @Size(min = 5, max = 40, message = "O campo 'title' deve ter entre 5 e 40 caracteres")
  private String title;

  @NotNull(message = "O campo 'description' é obrigatório")
  @NotBlank(message = "O campo 'description' não pode estar em branco")
  @Size(min = 5, max = 2000, message = "O campo 'description' deve ter entre 5 e 2000 caracteres")
  private String description;

  @Size(max = 255, message = "O campo 'capeImage' deve ter no máximo 255 caracteres")
  private String capeImage;

  @NotNull(message = "O campo 'tags' é obrigatório")
  @NotEmpty(message = "O curso deve ter ao menos uma tag")
  @Valid
  private List<IdDTO> tags;
}
