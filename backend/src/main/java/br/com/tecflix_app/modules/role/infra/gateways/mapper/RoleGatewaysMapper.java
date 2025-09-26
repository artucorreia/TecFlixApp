package br.com.tecflix_app.modules.role.infra.gateways.mapper;

import br.com.tecflix_app.modules.role.application.domain.entity.Role;
import br.com.tecflix_app.modules.role.infra.persistence.RoleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleGatewaysMapper {
  RoleEntity map(Role role);

  Role map(RoleEntity roleEntity);
}
