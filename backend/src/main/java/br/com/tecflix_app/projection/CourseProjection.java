package br.com.tecflix_app.projection;

import java.time.LocalDateTime;
import java.util.UUID;

public interface CourseProjection {
    UUID getId();
    String getTitle();
    String getCapeImage();
    LocalDateTime getCreatedAt();
    Double getTotalScoreReviews();
    Long getTotalReviews();
    UserBasicProjection getProfessor();
}
