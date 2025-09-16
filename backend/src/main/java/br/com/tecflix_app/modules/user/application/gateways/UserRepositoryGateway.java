package br.com.tecflix_app.modules.user.application.gateways;

import br.com.tecflix_app.modules.user.application.domain.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepositoryGateway {
  Optional<User> findById(UUID id);

  Optional<User> findByEmail(String email);

  User save(User user);
}
