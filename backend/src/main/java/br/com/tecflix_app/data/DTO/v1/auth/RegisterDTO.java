package br.com.tecflix_app.data.DTO.v1.auth;

import br.com.tecflix_app.model.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {
  @NotBlank
  @Size(min = 3, max = 30)
  private String name;

  @Email
  @Size(max = 50)
  private String email;

  @JsonIgnore private Role role = Role.USER;

  @NotBlank
  @Size(min = 8, max = 50)
  private String password;

  @JsonIgnore private LocalDateTime createdAt = LocalDateTime.now();

  @JsonIgnore private Boolean active = true;

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

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }
}
