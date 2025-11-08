package br.com.tecflix_app.modules.auth.infra.gateways;

import br.com.tecflix_app.modules.auth.application.domain.entity.TokenJwt;
import br.com.tecflix_app.modules.auth.application.gateways.TokenGateway;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TokenAuth0Gateway implements TokenGateway {

  @Override
  public TokenJwt generate(String secret, Duration duration, String issuer, UUID userId) {
    Algorithm algorithm = Algorithm.HMAC256(secret);
    Instant issuedAt = Instant.now();
    Instant expiresAt = issuedAt.plus(duration);
    String accessToken =
        JWT.create()
            .withIssuer(issuer)
            .withSubject(userId.toString())
            .withIssuedAt(issuedAt)
            .withExpiresAt(expiresAt)
            .sign(algorithm);

    return new TokenJwt(accessToken, issuedAt, expiresAt);
  }

  @Override
  public UUID validate(String secret, String issuer, String token) {
    Algorithm algorithm = Algorithm.HMAC256(secret);
    String subject = JWT.require(algorithm).withIssuer(issuer).build().verify(token).getSubject();
    return UUID.fromString(subject);
  }
}
