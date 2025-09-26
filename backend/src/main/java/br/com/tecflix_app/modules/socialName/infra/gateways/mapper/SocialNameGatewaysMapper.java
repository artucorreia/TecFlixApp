package br.com.tecflix_app.modules.socialName.infra.gateways.mapper;

import br.com.tecflix_app.modules.socialName.application.domain.entity.SocialName;
import br.com.tecflix_app.modules.socialName.infra.persistence.SocialNameEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SocialNameGatewaysMapper {
  SocialName map(SocialNameEntity socialEntity);

  SocialNameEntity map(SocialName social);
}
