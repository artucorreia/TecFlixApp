package br.com.tecflix_app.data.DTO.v1.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PixChargeRequest {
    @NotNull @NotBlank
    private String key;
    
    @NotNull @NotBlank
    private String value;
}
