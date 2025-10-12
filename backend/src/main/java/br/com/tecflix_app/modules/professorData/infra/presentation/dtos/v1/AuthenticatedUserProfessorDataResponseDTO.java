package br.com.tecflix_app.modules.professorData.infra.presentation.dtos.v1;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthenticatedUserProfessorDataResponseDTO {
  private Long id;
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
