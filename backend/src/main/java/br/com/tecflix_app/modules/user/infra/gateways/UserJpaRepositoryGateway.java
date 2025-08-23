package br.com.tecflix_app.modules.user.infra.gateways;

import br.com.tecflix_app.modules.user.application.domain.entity.User;
import br.com.tecflix_app.modules.user.application.gateways.UserRepositoryGateway;
import br.com.tecflix_app.modules.user.infra.gateways.mapper.UserGatewaysMapper;
import br.com.tecflix_app.modules.user.infra.persistence.UserEntity;
import br.com.tecflix_app.modules.user.infra.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserJpaRepositoryGateway implements UserRepositoryGateway {
  private final UserRepository userRepository;
  private final UserGatewaysMapper userGatewaysMapper;

  @Override
  public Optional<User> findById(UUID id) {
    Optional<UserEntity> userEntity = userRepository.findById(id);
    return userEntity.map(userGatewaysMapper::entityToDomain);
  }
}
