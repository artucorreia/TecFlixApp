package br.com.tecflix_app.modules.socialName.infra.gateways;

import br.com.tecflix_app.modules.socialName.application.domain.entity.SocialName;
import br.com.tecflix_app.modules.socialName.application.gateways.SocialNameRepositoryGateway;
import br.com.tecflix_app.modules.socialName.infra.gateways.mapper.SocialNameGatewaysMapper;
import br.com.tecflix_app.modules.socialName.infra.persistence.SocialNameEntity;
import br.com.tecflix_app.modules.socialName.infra.persistence.SocialNameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SocialNameJpaRepositoryGateway implements SocialNameRepositoryGateway {
  private final SocialNameRepository socialNameRepository;
  private final SocialNameGatewaysMapper socialNameGatewaysMapper;

  @Override
  public Optional<SocialName> findById(Long id) {
    Optional<SocialNameEntity> optionalSocialNameEntity = socialNameRepository.findById(id);
    return optionalSocialNameEntity.map(socialNameGatewaysMapper::map);
  }
}
