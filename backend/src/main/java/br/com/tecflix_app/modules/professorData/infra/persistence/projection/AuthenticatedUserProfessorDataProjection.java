package br.com.tecflix_app.modules.professorData.infra.persistence.projection;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public interface AuthenticatedUserProfessorDataProjection {
  Long getId();

  OccupationProjection getOccupation();

  String getOtherOccupation();

  String getBiography();

  LocalDate getBirthdate();

  GenderProjection getGender();

  String getOtherGender();

  String getProfileImageUrl();

  LocalDateTime getCreatedAt();

  LocalDateTime getUpdatedAt();

  interface OccupationProjection {
    String getName();
  }

  interface GenderProjection {
    String getName();
  }
}
