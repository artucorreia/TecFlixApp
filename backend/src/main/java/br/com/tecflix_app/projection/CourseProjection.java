package br.com.tecflix_app.projection;

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
