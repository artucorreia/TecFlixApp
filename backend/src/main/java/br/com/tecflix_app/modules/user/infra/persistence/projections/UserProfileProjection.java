package br.com.tecflix_app.modules.user.infra.persistence.projections;

import br.com.tecflix_app.modules.course.infra.persistence.projections.CourseProjection;
import br.com.tecflix_app.modules.professorData.infra.persistence.projection.ProfessorDataProjection;
import br.com.tecflix_app.modules.social.infra.persistence.projections.SocialProjection;

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

  interface SocialProjection {
    SocialNameProjection getSocialName();

    String getUrl();

    interface SocialNameProjection {
      String getName();
    }
  }
}
