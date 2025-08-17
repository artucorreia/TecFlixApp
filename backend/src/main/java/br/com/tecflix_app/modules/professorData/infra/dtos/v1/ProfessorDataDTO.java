package br.com.tecflix_app.modules.professorData.infra.dtos.v1;

import java.time.LocalDate;
import java.time.LocalDateTime;

import br.com.tecflix_app.modules.professorData.application.domain.enums.Gender;
import br.com.tecflix_app.modules.professorData.application.domain.enums.Occupation;
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
  private Gender gender;
  private String contact;
  private Occupation occupation;
  private String biography;
  private String profileImage;
  private LocalDateTime createdAt;
}
