package br.com.tecflix_app.modules.emailCode.application.domain.entity;

import br.com.tecflix_app.modules.user.application.domain.entity.User;
import java.io.Serializable;
import java.time.LocalDateTime;

public class EmailCode implements Serializable {
  private Long id;
  private String code;
  private LocalDateTime createdAt;
  private User user;

  public EmailCode() {}

  public EmailCode(Long id, String code, LocalDateTime createdAt, User user) {
    this.id = id;
    this.code = code;
    this.createdAt = createdAt;
    this.user = user;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
