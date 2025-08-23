package br.com.tecflix_app.modules.auth.application.gateways;

import java.util.Optional;
import java.util.UUID;

public interface AuthenticatedUserGateway {
  Optional<UUID> findId();
}
