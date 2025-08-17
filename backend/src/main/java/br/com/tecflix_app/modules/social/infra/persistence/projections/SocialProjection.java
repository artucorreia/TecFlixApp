package br.com.tecflix_app.modules.social.infra.persistence.projections;

import br.com.tecflix_app.modules.social.application.domain.enums.SocialName;

public interface SocialProjection {
  SocialName getName();

  String getUrl();
}
