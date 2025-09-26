package br.com.tecflix_app.modules.professorData.infra.presentation.dtos.v1;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfessorDataUserProfileResponseDTO {
  private UUID professorId;

  private String professorName;

  private String occupationName;

  private String OtherOccupation;

  private String biography;

  private LocalDate birthdate;

  private String genderName;

  private String otherGender;

  private String profileImageUrl;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;
}
