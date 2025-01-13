package br.com.tecflix_app.data.DTO.v1.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NewPasswordDTO {
    @NotNull
    @NotBlank
    @Size(min = 8, max = 50)
    private String newPassword;
}
