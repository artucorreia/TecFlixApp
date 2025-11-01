package br.com.tecflix_app.modules.auth.infra.gateways;

import br.com.tecflix_app.modules.auth.application.domain.entity.TokenJwt;
import br.com.tecflix_app.modules.auth.application.domain.exception.JwtCreationTokenException;
import br.com.tecflix_app.modules.auth.application.gateways.TokenGateway;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;
import java.util.logging.Logger;

@Component
@RequiredArgsConstructor
public class TokenAuth0Gateway implements TokenGateway {
  private final Logger LOGGER = Logger.getLogger(TokenAuth0Gateway.class.getName());

  @Value("${security.jwt.token.issuer}")
  private String issuer;

  @Value("${security.jwt.token.secret}")
  private String secret;

  @Value("${security.jwt.token.duration}")
  private Duration duration;

  @Override
  public TokenJwt generate(UUID userId) {
    LOGGER.info("Generating JWT token for user:" + userId);
    try {
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
    } catch (Exception e) {
      throw new JwtCreationTokenException("Ocorreu um erro durante a geração do token JWT");
    }
  }

  @Override
  public UUID validate(String token) {
    LOGGER.info("Validating JWT token");
    try {
      Algorithm algorithm = Algorithm.HMAC256(secret);
      String subject = JWT.require(algorithm).withIssuer(issuer).build().verify(token).getSubject();
      return UUID.fromString(subject);
    } catch (JWTVerificationException e) {
      LOGGER.warning("Erro na validação do token: " + e.getMessage());
      return null;
    }
  }
}
