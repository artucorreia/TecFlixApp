package br.com.tecflix_app.modules.professorData.infra.persistence.projection;

import br.com.tecflix_app.modules.professorData.application.domain.enums.Occupation;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface ProfessorDataProjection {
  LocalDate getBirthdate();

  Occupation getOccupation();

  String getBiography();

  String getProfileImage();

  LocalDateTime getCreatedAt();
}
