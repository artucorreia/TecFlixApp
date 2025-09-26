package br.com.tecflix_app.modules.course.infra.persistence.projections;

import br.com.tecflix_app.modules.user.infra.persistence.projections.UserBasicProjection;

import java.util.List;
import java.time.LocalDateTime;
import java.util.UUID;

public interface CourseDetailsProjection {
  UUID getId();

  String getTitle();

  String getDescription();

  String getCapeImage();

  Boolean getActive();

  LocalDateTime getCreatedAt();

  Long getTotalScore();

  Long getTotalReviews();

  Double getAverageScore();

  UserBasicProjection getProfessor();

  List<TagCourseDetailsProjection> getTags();

  public interface TagCourseDetailsProjection {
    Long getId();

    String getName();
  }
}
