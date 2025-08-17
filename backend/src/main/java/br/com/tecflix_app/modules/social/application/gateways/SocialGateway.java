package br.com.tecflix_app.modules.social.application.gateways;

import br.com.tecflix_app.modules.social.application.domain.entity.Social;
import java.util.List;

public interface SocialGateway {
  void save(Social social);

  void saveAll(List<Social> socials);
}
