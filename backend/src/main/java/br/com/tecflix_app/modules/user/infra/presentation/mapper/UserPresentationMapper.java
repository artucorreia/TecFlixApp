package br.com.tecflix_app.modules.user.infra.presentation.mapper;

import br.com.tecflix_app.modules.user.application.domain.entity.User;
import br.com.tecflix_app.modules.user.infra.presentation.dtos.v1.AuthenticatedUserResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserPresentationMapper {
  AuthenticatedUserResponseDTO map(User user);
}
