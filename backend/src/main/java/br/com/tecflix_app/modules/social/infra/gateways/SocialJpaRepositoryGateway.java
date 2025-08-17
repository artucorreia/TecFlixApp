package br.com.tecflix_app.modules.social.infra.gateways;

import br.com.tecflix_app.modules.social.application.domain.entity.Social;
import br.com.tecflix_app.modules.social.application.gateways.SocialGateway;
import br.com.tecflix_app.modules.social.infra.gateways.mapper.SocialGatewayMapper;
import br.com.tecflix_app.modules.social.infra.persistence.SocialEntity;
import br.com.tecflix_app.modules.social.infra.persistence.SocialRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class SocialJpaRepositoryGateway implements SocialGateway {
  private final SocialRepository socialRepository;
  private final SocialGatewayMapper socialGatewayMapper;

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void save(Social social) {
    SocialEntity socialEntity = socialGatewayMapper.toEntity(social);
    socialRepository.save(socialEntity);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void saveAll(List<Social> socials) {
    List<SocialEntity> socialEntities = socialGatewayMapper.toEntity(socials);
    socialRepository.saveAll(socialEntities);
  }
}
