package br.com.tecflix_app.modules.user.infra.presentation.dtos.v1;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import br.com.tecflix_app.modules.professorData.infra.presentation.dtos.v1.AuthenticatedUserProfessorDataResponseDTO;
import br.com.tecflix_app.modules.social.infra.presentation.dtos.v1.AuthenticatedUserSocialResponseDTO;
import br.com.tecflix_app.modules.social.infra.presentation.dtos.v1.SocialResponseDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthenticatedUserResponseDTO {
  private UUID id;
  private String name;
  private String email;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private Boolean isProfessor;
  private AuthenticatedUserProfessorDataResponseDTO professorData;
  private List<AuthenticatedUserSocialResponseDTO> socials;
}
