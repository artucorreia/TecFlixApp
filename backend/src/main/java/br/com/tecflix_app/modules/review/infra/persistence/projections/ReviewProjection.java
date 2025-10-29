package br.com.tecflix_app.modules.review.infra.persistence.projections;

import java.time.LocalDateTime;
import java.util.UUID;

public interface ReviewProjection {
  Long getId();

  UserProjection getUser();

  Integer getScore();

  String getComment();

  LocalDateTime getCreatedAt();

  LocalDateTime getUpdatedAt();

  interface UserProjection {
    UUID getId();

    String getName();
  }
}
