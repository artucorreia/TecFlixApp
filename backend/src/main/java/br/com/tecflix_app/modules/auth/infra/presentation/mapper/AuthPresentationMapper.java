package br.com.tecflix_app.modules.auth.infra.presentation.mapper;

import br.com.tecflix_app.modules.auth.infra.presentation.dtos.v1.RegisterDTO;
import br.com.tecflix_app.modules.user.application.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface AuthPresentationMapper {
  @Mappings(
      value = {
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "roles", ignore = true),
        @Mapping(target = "createdAt", ignore = true),
      })
  User map(RegisterDTO user);
}
