package br.com.tecflix_app.modules.course.infra.persistence.projections;

import br.com.tecflix_app.modules.user.infra.persistence.projections.UserBasicProjection;

import java.time.LocalDateTime;
import java.util.UUID;

public interface CourseProjection {
  UUID getId();

  String getTitle();

  String getCapeImage();

  LocalDateTime getCreatedAt();

  Long getTotalScore();

  Long getTotalReviews();

  Double getAverageScore();

  UserBasicProjection getProfessor();
}
