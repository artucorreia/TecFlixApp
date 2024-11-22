package br.com.tecflix_app.data.DTO.v1.auth;

import java.time.Instant;
import java.util.UUID;

import br.com.tecflix_app.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class TokenDTO {
    private UUID userId;
    private Role userRole;
    private String token;
    private Instant createdAt;
    private Instant expiresAt;
}
