package br.com.tecflix_app.modules.user.infra.presentation.dtos.v1;

import java.util.Set;

import br.com.tecflix_app.modules.professorData.infra.presentation.dtos.v1.CreateProfessorDataDTO;
import br.com.tecflix_app.modules.social.infra.presentation.dtos.v1.CreateSocialDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class RegisterProfessorDTO {
  @NotNull(message = "O campo 'professorData' é obrigatório")
  @Valid
  private CreateProfessorDataDTO professorData;

  @Valid private Set<CreateSocialDTO> socials;
}
