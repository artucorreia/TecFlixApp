package br.com.tecflix_app.modules.auth.domain.entity;

import br.com.tecflix_app.modules.user.application.domain.entity.User;
import java.io.Serializable;
import java.time.Instant;

public class RefreshToken implements Serializable {
  private Long id;
  private String token;
  private Instant expiresAt;
  private User user;

  public RefreshToken() {}

  public RefreshToken(Long id, String token, Instant expiresAt, User user) {
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
}
