package br.com.tecflix_app.projection;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface UserProfileProjection {
    UUID getId();

    String getName();

    LocalDateTime getCreatedAt();

    ProfessorDataProjection getProfessorData();

    List<SocialProjection> getSocials();

    List<CourseProjection> getCoursesTaught();

}
