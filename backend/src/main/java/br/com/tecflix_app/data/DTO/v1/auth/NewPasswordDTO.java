package br.com.tecflix_app.data.DTO.v1.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class NewPasswordDTO {
  @NotNull
  @NotBlank
  @Size(min = 8, max = 50)
  private String newPassword;

  public NewPasswordDTO() {}

  public NewPasswordDTO(@NotNull @NotBlank @Size(min = 8, max = 50) String newPassword) {
    this.newPassword = newPassword;
  }

  public String getNewPassword() {
    return newPassword;
  }

  public void setNewPassword(String newPassword) {
    this.newPassword = newPassword;
  }
}
