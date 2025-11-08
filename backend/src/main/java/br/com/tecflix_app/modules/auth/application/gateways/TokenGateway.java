package br.com.tecflix_app.modules.auth.application.gateways;

import br.com.tecflix_app.modules.auth.application.domain.entity.TokenJwt;

import java.time.Duration;
import java.util.UUID;

public interface TokenGateway {
  TokenJwt generate(String secret, Duration duration, String issuer, UUID userId);

  UUID validate(String secret, String issuer, String token);
}
