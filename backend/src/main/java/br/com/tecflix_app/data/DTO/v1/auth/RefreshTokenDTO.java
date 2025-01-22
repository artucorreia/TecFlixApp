package br.com.tecflix_app.data.DTO.v1.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RefreshTokenDTO {
  @NotBlank
  @NotNull
  @Size(max = 36)
  private String token;

  public RefreshTokenDTO(@NotBlank @NotNull @Size(max = 36) String token) {
    this.token = token;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}
