package br.com.tecflix_app.data.DTO.v1.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class RefreshTokenDTO {
    @NotBlank
    @NotNull
    @Size(max = 36)
    private String token;
}
