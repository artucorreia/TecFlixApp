package br.com.tecflix_app.data.DTO.v1.auth;

import java.time.Instant;
import java.util.UUID;

public class TokenDTO {
  private UUID userId;
  private String accessToken;
  private String refreshToken;
  private Instant createdAt;
  private Instant expiresAt;

  public TokenDTO() {}

  public TokenDTO(
      UUID userId, String accessToken, String refreshToken, Instant createdAt, Instant expiresAt) {
    this.userId = userId;
    this.accessToken = accessToken;
    this.refreshToken = refreshToken;
    this.createdAt = createdAt;
    this.expiresAt = expiresAt;
  }

  public UUID getUserId() {
    return userId;
  }

  public void setUserId(UUID userId) {
    this.userId = userId;
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

  public Instant getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Instant createdAt) {
    this.createdAt = createdAt;
  }

  public Instant getExpiresAt() {
    return expiresAt;
  }

  public void setExpiresAt(Instant expiresAt) {
    this.expiresAt = expiresAt;
  }
}
