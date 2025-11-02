package br.com.tecflix_app.config.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class EnvConfigProvider implements ConfigProvider {
  // api
  @Value("${security.api.key}")
  private String apiKey;

  // token jwt
  @Value("${security.jwt.token.issuer}")
  private String tokenIssuer;

  @Value("${security.jwt.token.secret}")
  private String tokenSecret;

  @Value("${security.jwt.token.duration}")
  private Duration tokenDuration;

  // refresh token
  @Value("${security.jwt.token.refresh.duration}")
  private Duration refreshTokenDuration;

  // email
  @Value("${email.code.validation.time}")
  private Duration emailCodeValidationTime;

  // frontend
  @Value("${app.frontend.url}")
  private String frontendUrl;

  @Override
  public String getApiKey() {
    return apiKey;
  }

  @Override
  public Duration getRefreshTokenDuration() {
    return refreshTokenDuration;
  }

  @Override
  public Duration getTokenDuration() {
    return tokenDuration;
  }

  @Override
  public String getTokenIssuer() {
    return tokenIssuer;
  }

  @Override
  public String getTokenSecret() {
    return tokenSecret;
  }

  @Override
  public Duration getEmailCodeValidationTime() {
    return emailCodeValidationTime;
  }

  @Override
  public String getFrontEndUrl() {
    return frontendUrl;
  }
}
