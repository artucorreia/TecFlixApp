package br.com.tecflix_app.modules.auth.application.gateways;

import br.com.tecflix_app.modules.auth.application.domain.entity.TokenJwt;

import java.util.UUID;

public interface TokenGateway {
  TokenJwt generate(UUID userId);

  UUID validate(String token);
}
