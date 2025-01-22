package br.com.tecflix_app.data.DTO.v1.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class AuthenticationDTO {
  @Email private String email;

  @Size(min = 8, max = 50)
  private String password;

  public AuthenticationDTO(@Email String email, @Size(min = 8, max = 50) String password) {
    this.email = email;
    this.password = password;
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
}
