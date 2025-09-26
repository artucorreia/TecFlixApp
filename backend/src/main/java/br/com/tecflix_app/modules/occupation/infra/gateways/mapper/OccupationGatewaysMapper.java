package br.com.tecflix_app.modules.occupation.infra.gateways.mapper;

import br.com.tecflix_app.modules.occupation.application.domain.entity.Occupation;
import br.com.tecflix_app.modules.occupation.infra.persistence.OccupationEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OccupationGatewaysMapper {
  Occupation map(OccupationEntity occupationEntity);

  OccupationEntity map(Occupation occupation);
}
