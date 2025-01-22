package br.com.tecflix_app.data.DTO.v1.auth;

import br.com.tecflix_app.data.DTO.v1.response.UserDTO;
import java.time.LocalDateTime;

public class EmailCodeDTO {
  private String code;
  private UserDTO user;
  private LocalDateTime createdAt;

  public EmailCodeDTO() {}

  public EmailCodeDTO(String code, UserDTO user, LocalDateTime createdAt) {
    this.code = code;
    this.user = user;
    this.createdAt = createdAt;
  }

  public String getCode() {
    return code;
  }

  public UserDTO getUser() {
    return user;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }
}
