package br.com.tecflix_app.modules.social.infra.dtos.v1.response;

import br.com.tecflix_app.modules.social.application.domain.enums.SocialName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SocialResponse {
  private Long id;
  private SocialName name;
  private String url;
}
