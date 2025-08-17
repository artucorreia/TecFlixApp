package br.com.tecflix_app.modules.social.infra.dtos.v1.request;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.tecflix_app.modules.user.infra.dtos.v1.UserDTO;
import br.com.tecflix_app.modules.social.application.domain.enums.SocialName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateSocialRequest {
  @JsonIgnore private UserDTO user;

  @NotNull private SocialName name;

  @NotNull
  @NotBlank
  @Size(min = 5, max = 255)
  private String url;
}
