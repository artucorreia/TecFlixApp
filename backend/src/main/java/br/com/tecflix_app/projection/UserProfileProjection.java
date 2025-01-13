package br.com.tecflix_app.projection;

import java.time.LocalDateTime;
import java.util.UUID;

public interface UserProfileProjection {
    UUID getId();

    String getName();

    LocalDateTime getCreatedAt();

}
