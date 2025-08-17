package br.com.tecflix_app.modules.user.infra.persistence.projections;

import java.time.LocalDateTime;
import java.util.UUID;

public interface UserAccountProjection {
  UUID getId();

  String getName();

  String getEmail();

  LocalDateTime getCreatedAt();
}
