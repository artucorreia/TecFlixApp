package br.com.tecflix_app.modules.auth.application.gateways;

import br.com.tecflix_app.modules.user.application.domain.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface AuthenticatedUserGateway {
  Optional<UUID> findId();

  Optional<User> findUser();
}
