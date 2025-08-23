package br.com.tecflix_app.modules.course.infra.dtos.v1;

import java.util.List;

import br.com.tecflix_app.modules.shared.dto.v1.IdDTO;
import br.com.tecflix_app.modules.tag.infra.presentation.dtos.v1.response.TagDTOResponse;
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
  @NotBlank(message = "O campo de título é obrigatório")
  @Size(min = 5, max = 40, message = "O campo de título deve ter entre 5 e 40 caracteres")
  private String title;

  @NotBlank(message = "O campo de descrição é obrigatório")
  @Size(min = 5, max = 2000, message = "O campo de descrição deve ter entre 5 e 2000 caracteres")
  private String description;

  @Size(max = 255, message = "A url da capa deve ter até no máximo 255 caracteres")
  private String capeImage;

  @NotEmpty(message = "O curso deve ter ao menos uma tag")
  @Valid
  private List<IdDTO> tags;
}
