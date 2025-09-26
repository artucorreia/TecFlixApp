package br.com.tecflix_app.modules.course.infra.persistence.projections;

import java.time.LocalDateTime;
import java.util.UUID;

public interface CourseUserProfileProjection {
  UUID getId();

  String getTitle();

  String getCapeImageUrl();

  Long getTotalScore();

  Long getTotalReviews();

  Double getAverageScore();

  ProfessorProjection getProfessor();

  LocalDateTime getCreatedAt();

  LocalDateTime getUpdatedAt();

  interface ProfessorProjection {
    UUID getId();

    String getName();
  }
}
