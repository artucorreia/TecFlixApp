package br.com.tecflix_app.modules.professorData.infra.persistence.projection;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface ProfessorDataProjection {
  LocalDate getBirthdate();

  OccupationProjection getOccupation();

  String getBiography();

  String getProfileImage();

  LocalDateTime getCreatedAt();

  interface OccupationProjection {
    String getName();
  }
}
