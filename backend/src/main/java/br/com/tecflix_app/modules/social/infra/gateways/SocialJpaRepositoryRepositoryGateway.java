package br.com.tecflix_app.modules.social.infra.gateways;

import br.com.tecflix_app.modules.social.application.domain.entity.Social;
import br.com.tecflix_app.modules.social.application.gateways.SocialRepositoryGateway;
import br.com.tecflix_app.modules.social.infra.gateways.mapper.SocialGatewayMapper;
import br.com.tecflix_app.modules.social.infra.persistence.SocialEntity;
import br.com.tecflix_app.modules.social.infra.persistence.SocialRepository;
import java.util.List;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class SocialJpaRepositoryRepositoryGateway implements SocialRepositoryGateway {
  private final SocialRepository socialRepository;
  private final SocialGatewayMapper socialGatewayMapper;

  @Override
  public List<Social> findProfileByUserId(UUID userId) {
    return socialRepository.findSocialUserProfileProjectionByUserId(userId).stream()
        .map(socialGatewayMapper::map)
        .toList();
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void save(Social social) {
    SocialEntity socialEntity = socialGatewayMapper.map(social);
    socialRepository.save(socialEntity);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void saveAll(List<Social> socials) {
    List<SocialEntity> socialEntities = socialGatewayMapper.map(socials);
    socialRepository.saveAll(socialEntities);
  }
}
