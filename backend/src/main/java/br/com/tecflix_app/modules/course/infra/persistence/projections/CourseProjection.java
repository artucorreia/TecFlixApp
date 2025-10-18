package br.com.tecflix_app.modules.course.infra.persistence.projections;

import java.time.LocalDateTime;
import java.util.UUID;

public interface CourseProjection {
  UUID getId();

  String getTitle();

  String getCapeImageUrl();

  LocalDateTime getCreatedAt();

  LocalDateTime getUpdatedAt();

  Long getTotalScore();

  Long getTotalReviews();

  Double getAverageScore();

  ProfessorProjection getProfessor();

  interface ProfessorProjection {
    UUID getId();

    String getName();
  }
}
