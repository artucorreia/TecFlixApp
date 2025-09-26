package br.com.tecflix_app.modules.social.application.gateways;

import br.com.tecflix_app.modules.social.application.domain.entity.Social;
import java.util.List;
import java.util.UUID;

public interface SocialRepositoryGateway {
  List<Social> findProfileByUserId(UUID userId);

  void save(Social social);

  void saveAll(List<Social> socials);
}
