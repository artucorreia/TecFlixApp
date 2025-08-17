package br.com.tecflix_app.modules.user.application.domain.enums;

public enum Role {
  ADMIN("admin"),
  PROFESSOR("professor"),
  USER("user");

  private String role;

  Role(String role) {
    this.role = role;
  }

  public String getRole() {
    return role;
  }
}
