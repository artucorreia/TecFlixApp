package br.com.tecflix_app.modules.gender.infra.gateways.mapper;

import br.com.tecflix_app.modules.gender.application.domain.entity.Gender;
import br.com.tecflix_app.modules.gender.infra.persistence.GenderEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GenderGatewaysMapper {
  Gender map(GenderEntity genderEntity);

  GenderEntity map(Gender genderEntity);
}
