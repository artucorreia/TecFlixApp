package br.com.tecflix_app.projection;

import java.time.LocalDate;
import java.time.LocalDateTime;

import br.com.tecflix_app.model.enums.Occupation;

public interface ProfessorDataProjection {
    LocalDate getBirthdate();

    Occupation getOccupation();

    String getBiography();

    String getProfileImage();

    LocalDateTime getCreatedAt();
}
