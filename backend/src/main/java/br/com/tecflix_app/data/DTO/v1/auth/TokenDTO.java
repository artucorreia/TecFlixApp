package br.com.tecflix_app.data.DTO.v1.auth;

import java.time.Instant;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class TokenDTO {
    private UUID userId;
    private String accessToken;
    private String refreshToken;
    private Instant createdAt;
    private Instant expiresAt;
}
