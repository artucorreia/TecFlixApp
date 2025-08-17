package br.com.tecflix_app.modules.social.infra.gateways.mapper;

import br.com.tecflix_app.modules.social.application.domain.entity.Social;
import br.com.tecflix_app.modules.social.infra.persistence.SocialEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SocialGatewayMapper {
  SocialEntity toEntity(Social social);
  List<SocialEntity> toEntity(List<Social> social);

}
