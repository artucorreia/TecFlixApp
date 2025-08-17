package br.com.tecflix_app.modules.review.infra.persistence.projections;

import br.com.tecflix_app.modules.user.infra.persistence.projections.UserBasicProjection;

import java.time.LocalDateTime;

public interface ReviewProjection {
  Long getId();

  Double getScore();

  String getComment();

  LocalDateTime getCreatedAt();

  UserBasicProjection getUser();
}
