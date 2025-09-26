package br.com.tecflix_app.modules.user.application.domain.entity;

import br.com.tecflix_app.modules.role.application.domain.entity.Role;
import br.com.tecflix_app.modules.shared.application.domain.entity.BaseDomainEntity;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class User extends BaseDomainEntity {
  private UUID id;
  private String name;
  private String email;
  private String password;
  private Set<Role> roles;
  private Boolean emailVerified;
  private LocalDateTime emailVerifiedAt;

  public User() {}

  public User(
      UUID createdBy,
      LocalDateTime createdAt,
      UUID updatedBy,
      LocalDateTime updatedAt,
      Boolean deleted,
      UUID id,
      String name,
      String email,
      String password,
      Set<Role> roles,
      Boolean emailVerified,
      LocalDateTime emailVerifiedAt) {
    super(createdBy, createdAt, updatedBy, updatedAt, deleted);
    this.id = id;
    this.name = name;
    this.email = email;
    this.password = password;
    this.roles = roles;
    this.emailVerified = emailVerified;
    this.emailVerifiedAt = emailVerifiedAt;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

  public Boolean getEmailVerified() {
    return emailVerified;
  }

  public void setEmailVerified(Boolean emailVerified) {
    this.emailVerified = emailVerified;
  }

  public LocalDateTime getEmailVerifiedAt() {
    return emailVerifiedAt;
  }

  public void setEmailVerifiedAt(LocalDateTime emailVerifiedAt) {
    this.emailVerifiedAt = emailVerifiedAt;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    User user = (User) o;
    return Objects.equals(id, user.id)
        && Objects.equals(name, user.name)
        && Objects.equals(email, user.email)
        && Objects.equals(password, user.password)
        && Objects.equals(roles, user.roles)
        && Objects.equals(emailVerified, user.emailVerified)
        && Objects.equals(emailVerifiedAt, user.emailVerifiedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        super.hashCode(), id, name, email, password, roles, emailVerified, emailVerifiedAt);
  }
}
