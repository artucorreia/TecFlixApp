package br.com.tecflix_app.modules.social.infra.presentation.dtos.v1;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthenticatedUserSocialResponseDTO {
  private Long id;
  private String socialName;
  private String url;
}
