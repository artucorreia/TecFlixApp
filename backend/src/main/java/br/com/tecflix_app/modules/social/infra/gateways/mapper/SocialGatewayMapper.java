package br.com.tecflix_app.modules.social.infra.gateways.mapper;

import br.com.tecflix_app.modules.social.application.domain.entity.Social;
import br.com.tecflix_app.modules.social.infra.persistence.SocialEntity;
import br.com.tecflix_app.modules.social.infra.persistence.projections.SocialUserProfileProjection;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SocialGatewayMapper {
  Social map(SocialUserProfileProjection socialUserProfileProjection);

  SocialEntity map(Social social);

  List<SocialEntity> map(List<Social> social);
}
