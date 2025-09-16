package br.com.tecflix_app.modules.user.infra.dtos.v1;

import java.time.LocalDateTime;
import java.util.List;

import br.com.tecflix_app.modules.professorData.infra.dtos.v1.CreateProfessorDataDTO;
import br.com.tecflix_app.modules.social.infra.dtos.v1.request.CreateSocialRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;

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

  @Valid private List<CreateSocialRequest> socials;

  @JsonIgnore private LocalDateTime createdAt = LocalDateTime.now();
}
