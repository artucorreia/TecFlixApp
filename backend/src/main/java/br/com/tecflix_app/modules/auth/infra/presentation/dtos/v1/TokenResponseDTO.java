package br.com.tecflix_app.modules.auth.infra.presentation.dtos.v1;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TokenResponseDTO {
  private String accessToken;
  private String refreshToken;
  private Instant issuedAt;
  private Instant expiresAt;
}
