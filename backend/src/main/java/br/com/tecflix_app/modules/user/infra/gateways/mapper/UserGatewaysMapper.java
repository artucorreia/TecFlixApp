package br.com.tecflix_app.modules.user.infra.gateways.mapper;

import br.com.tecflix_app.modules.user.application.domain.entity.User;
import br.com.tecflix_app.modules.user.infra.persistence.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserGatewaysMapper {
  @Mappings(value = {
      @Mapping(target = "coursesTaught", ignore = true),
      @Mapping(target = "enrolledCourses", ignore = true)
  })
  User entityToDomain(UserEntity userEntity);

  UserEntity domainToEntity(User user);
}
