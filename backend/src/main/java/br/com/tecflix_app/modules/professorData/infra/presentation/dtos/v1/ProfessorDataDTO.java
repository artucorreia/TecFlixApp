package br.com.tecflix_app.modules.professorData.infra.presentation.dtos.v1;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProfessorDataDTO {
  private String cpf;
  private LocalDate birthdate;
  private String gender;
  private String contact;
  private String occupation;
  private String biography;
  private String profileImage;
  private LocalDateTime createdAt;
}
