package br.com.tecflix_app.modules.auth.application.domain.entity;

import java.time.Instant;

public class TokenJwt {
  private String accessToken;
  private String refreshToken;
  private Instant issuedAt;
  private Instant expiresAt;

  public TokenJwt() {}

  public TokenJwt(String accessToken, Instant issuedAt, Instant expiresAt) {
    this.accessToken = accessToken;
    this.issuedAt = issuedAt;
    this.expiresAt = expiresAt;
  }

  public TokenJwt(String accessToken, String refreshToken, Instant issuedAt, Instant expiresAt) {
    this.accessToken = accessToken;
    this.refreshToken = refreshToken;
    this.issuedAt = issuedAt;
    this.expiresAt = expiresAt;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  public Instant getIssuedAt() {
    return issuedAt;
  }

  public void setIssuedAt(Instant issuedAt) {
    this.issuedAt = issuedAt;
  }

  public Instant getExpiresAt() {
    return expiresAt;
  }

  public void setExpiresAt(Instant expiresAt) {
    this.expiresAt = expiresAt;
  }
}
