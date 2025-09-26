package br.com.tecflix_app.modules.social.infra.presentation.dtos.v1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SocialResponseDTO {
  private Long id;
//  private SocialName name;
  private String url;
}
