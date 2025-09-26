package br.com.tecflix_app.modules.user.infra.gateways.mapper;

import br.com.tecflix_app.modules.user.application.domain.entity.User;
import br.com.tecflix_app.modules.user.infra.persistence.UserEntity;
import br.com.tecflix_app.modules.user.infra.persistence.projections.UserBasicProjection;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserGatewaysMapper {
  @Named(value = "userBasicProjectionToUser")
  @Mappings(
      value = {
        @Mapping(target = "email", ignore = true),
        @Mapping(target = "password", ignore = true),
        @Mapping(target = "roles", ignore = true),
        @Mapping(target = "createdAt", ignore = true),
      })
  User map(UserBasicProjection userBasicProjection);

  User map(UserEntity userEntity);

  UserEntity map(User user);
}
