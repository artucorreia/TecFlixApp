package br.com.tecflix_app.modules.auth.domain.entity;

import br.com.tecflix_app.modules.shared.application.domain.entity.BaseDomainEntity;
import br.com.tecflix_app.modules.user.application.domain.entity.User;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class RefreshToken extends BaseDomainEntity {
  private Long id;
  private String token;
  private Instant expiresAt;
  private User user;

  public RefreshToken() {}

  public RefreshToken(
      UUID createdBy,
      LocalDateTime createdAt,
      UUID updatedBy,
      LocalDateTime updatedAt,
      Boolean deleted,
      Long id,
      String token,
      Instant expiresAt,
      User user) {
    super(createdBy, createdAt, updatedBy, updatedAt, deleted);
    this.id = id;
    this.token = token;
    this.expiresAt = expiresAt;
    this.user = user;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public Instant getExpiresAt() {
    return expiresAt;
  }

  public void setExpiresAt(Instant expiresAt) {
    this.expiresAt = expiresAt;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    RefreshToken that = (RefreshToken) o;
    return Objects.equals(id, that.id)
        && Objects.equals(token, that.token)
        && Objects.equals(expiresAt, that.expiresAt)
        && Objects.equals(user, that.user);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), id, token, expiresAt, user);
  }
}
