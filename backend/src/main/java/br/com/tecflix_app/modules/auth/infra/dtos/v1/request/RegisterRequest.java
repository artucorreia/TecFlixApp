package br.com.tecflix_app.modules.auth.infra.dtos.v1.request;

import br.com.tecflix_app.modules.user.application.domain.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class RegisterRequest {
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
}
