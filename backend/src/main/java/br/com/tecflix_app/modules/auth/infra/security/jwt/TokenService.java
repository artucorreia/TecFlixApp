package br.com.tecflix_app.modules.auth.infra.security.jwt;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;
import java.util.logging.Logger;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.tecflix_app.modules.auth.infra.presentation.dtos.v1.TokenResponseDTO;
import br.com.tecflix_app.modules.auth.domain.exception.JwtCreationTokenException;

@Service
@RequiredArgsConstructor
public class TokenService {
  private final Logger LOGGER = Logger.getLogger(TokenService.class.getName());
  private final RefreshTokenService refreshTokenService;

  @Value("${security.jwt.token.issuer}")
  private String issuer;

  @Value("${security.jwt.token.secret}")
  private String secret;

  @Value("${security.jwt.token.duration}")
  private Duration duration;

  @Transactional(rollbackFor = Exception.class)
  public TokenResponseDTO generateToken(UUID userId) {
    LOGGER.info("Generating JWT token");

    try {
      Algorithm algorithm = Algorithm.HMAC256(secret);
      Instant issuedAt = Instant.now();
      Instant expiresAt = issuedAt.plus(duration);
      String token =
          JWT.create()
              .withIssuer(issuer)
              .withSubject(userId.toString())
              .withIssuedAt(issuedAt)
              .withExpiresAt(expiresAt)
              .sign(algorithm);

      return TokenResponseDTO.builder()
          .accessToken(token)
          .refreshToken(refreshTokenService.create(userId).getToken())
          .issuedAt(issuedAt)
          .expiresAt(expiresAt)
          .build();
    } catch (Exception e) {
      throw new JwtCreationTokenException("Erro durante a geração do token");
    }
  }

  public UUID validateToken(String token) {
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
