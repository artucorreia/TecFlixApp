package br.com.tecflix_app.modules.auth.infra.gateways.mapper;

import br.com.tecflix_app.modules.auth.application.domain.entity.RefreshToken;
import br.com.tecflix_app.modules.auth.infra.persistence.RefreshTokenEntity;
import br.com.tecflix_app.modules.user.application.domain.entity.User;
import br.com.tecflix_app.modules.user.infra.persistence.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthGatewaysMapper {
  UserEntity map(User user);

  User map(UserEntity user);

  RefreshToken map(RefreshTokenEntity refreshTokenEntity);

  RefreshTokenEntity map(RefreshToken refreshToken);
}
