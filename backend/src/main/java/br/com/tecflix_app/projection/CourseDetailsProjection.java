package br.com.tecflix_app.projection;

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
    Double getTotalScoreReviews();
    Long getTotalReviews();
    UserBasicProjection getUser();
    List<ModuleCourseDetailsProjection> getModules();
    List<TagCourseDetailsProjection> getTags();

    public interface TagCourseDetailsProjection {
        Long getId();
        String getName();
    }

    public interface ModuleCourseDetailsProjection {
        Long getId();
        String getTitle();
    }
}
