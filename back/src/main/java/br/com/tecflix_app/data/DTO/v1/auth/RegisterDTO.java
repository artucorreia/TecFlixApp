package br.com.tecflix_app.data.DTO.v1.auth;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.tecflix_app.model.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class RegisterDTO {
    @NotBlank
    @Size(min = 3, max = 30)
    private String name;

    @Email
    @Size(max = 50)
    private String email;

    @JsonIgnore
    private Role role = Role.USER;

    @NotBlank
    @Size(min = 8, max = 50)
    private String password;

    @JsonIgnore
    private LocalDateTime createdAt = LocalDateTime.now();

    @JsonIgnore
    private Boolean active = true;
}
