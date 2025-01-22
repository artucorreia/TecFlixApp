package br.com.tecflix_app.data.DTO.v1.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PixChargeRequest {
  @NotNull @NotBlank private String key;

  @NotNull @NotBlank private String value;

  public PixChargeRequest(@NotNull @NotBlank String key, @NotNull @NotBlank String value) {
    this.key = key;
    this.value = value;
  }

  public String getKey() {
    return key;
  }

  public String getValue() {
    return value;
  }
}
