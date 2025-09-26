package br.com.tecflix_app.modules.socialName.application.gateways;

import br.com.tecflix_app.modules.socialName.application.domain.entity.SocialName;

import java.util.Optional;

public interface SocialNameRepositoryGateway {
  Optional<SocialName> findById(Long id);
}
