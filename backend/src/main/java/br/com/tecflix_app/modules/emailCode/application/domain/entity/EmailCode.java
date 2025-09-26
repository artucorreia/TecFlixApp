package br.com.tecflix_app.modules.emailCode.application.domain.entity;

import br.com.tecflix_app.modules.shared.application.domain.entity.BaseDomainEntity;
import br.com.tecflix_app.modules.user.application.domain.entity.User;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class EmailCode extends BaseDomainEntity {
  private Long id;
  private String code;
  private User user;

  public EmailCode() {}

  public EmailCode(
      UUID createdBy,
      LocalDateTime createdAt,
      UUID updatedBy,
      LocalDateTime updatedAt,
      Boolean deleted,
      Long id,
      String code,
      User user) {
    super(createdBy, createdAt, updatedBy, updatedAt, deleted);
    this.id = id;
    this.code = code;
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
    EmailCode emailCode = (EmailCode) o;
    return Objects.equals(id, emailCode.id)
        && Objects.equals(code, emailCode.code)
        && Objects.equals(user, emailCode.user);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), id, code, user);
  }
}
