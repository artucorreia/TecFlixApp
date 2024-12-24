package br.com.tecflix_app.service.auth.jwt;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.tecflix_app.data.DTO.v1.auth.TokenDTO;
import br.com.tecflix_app.exception.auth.JwtCreationTokenException;

@Service
public class TokenService {
    private final Logger LOGGER = Logger.getLogger(TokenService.class.getName());    
    
    private String subject;

    @Value("${security.jwt.token.secret}")
    private String secretKey;

    @Value("${security.jwt.token.duration}")
    private Long tokenDuration;

    private final RefreshTokenService refreshTokenService;

    @Autowired
    public TokenService(RefreshTokenService refreshTokenService) {
        this.refreshTokenService = refreshTokenService;
    }

    @Transactional(rollbackFor = Exception.class)
    public TokenDTO generateToken(UUID userId) {
        LOGGER.info("Generating token");

        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            Instant createdAt = getIssueDate();
            Instant expireAt = generateExpirationDate(createdAt);
            String token = JWT.create()
                    .withIssuer("grafmarques")
                    .withSubject(userId.toString())
                    .withIssuedAt(createdAt)
                    .withExpiresAt(expireAt)
                    .sign(algorithm);


            return TokenDTO.builder()
                .userId(userId)
                .accessToken(token)
                .refreshToken(
                    refreshTokenService.create(userId).getToken()
                )
                .createdAt(createdAt)
                .expiresAt(expireAt)
                .build();
        }
        catch (Exception e) {
            throw new JwtCreationTokenException("Erro durante a geração do token");
        }
    }

    public UUID validateToken(String token) {
        LOGGER.info("Validating token");

        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            subject = JWT.require(algorithm)
                    .withIssuer("grafmarques")
                    .build()
                    .verify(token)
                    .getSubject();
            return UUID.fromString(subject);
        }
        catch (JWTVerificationException e) {
            LOGGER.warning("Erro na validação do token: " + e.getMessage());
            return null;
        }
    }

    private Instant generateExpirationDate(Instant createdAt) { 
        return createdAt.plus(Duration.ofMillis(tokenDuration)); 
    }

    private Instant getIssueDate() { return Instant.now(); }

    public UUID getUserId() { return UUID.fromString(subject); }
}
