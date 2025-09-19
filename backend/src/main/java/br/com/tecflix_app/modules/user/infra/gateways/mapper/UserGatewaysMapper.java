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
        @Mapping(target = "role", ignore = true),
        @Mapping(target = "createdAt", ignore = true),
        @Mapping(target = "active", ignore = true),
        @Mapping(target = "refreshToken", ignore = true),
        @Mapping(target = "emailCode", ignore = true),
        @Mapping(target = "enrolledCourses", ignore = true),
        @Mapping(target = "professorData", ignore = true),
        @Mapping(target = "socials", ignore = true),
        @Mapping(target = "coursesTaught", ignore = true),
        @Mapping(target = "reviews", ignore = true),
      })
  User basicProjectionToDomain(UserBasicProjection userBasicProjection);

  @Named("entityToDomainWithoutCourses")
  @Mappings(
      value = {
        @Mapping(target = "password", ignore = true),
        @Mapping(target = "role", ignore = true),
        @Mapping(target = "refreshToken", ignore = true),
        @Mapping(target = "coursesTaught", ignore = true),
        @Mapping(target = "socials", ignore = true),
        @Mapping(target = "enrolledCourses", ignore = true),
      })
  User entityToDomain(UserEntity userEntity);

  User entityToDomainWithCourses(UserEntity userEntity);

  UserEntity domainToEntity(User user);
}
