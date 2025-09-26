package br.com.tecflix_app.modules.professorData.infra.presentation.dtos.v1;

import java.time.LocalDate;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateProfessorDataDTO {
  @NotNull(message = "O campo 'occupationId' é obrigatório")
  @Positive(message = "O campo 'occupationId' deve ser um número válido")
  private Long occupationId;

  @Size(min = 3, max = 50, message = "O campo 'otherOccupation' deve ter entre 3 e 50 caracteres")
  private String otherOccupation;

  @NotNull(message = "O campo 'biography' é obrigatório")
  @NotBlank(message = "O campo 'biography' não pode estar em branco")
  @Size(min = 10, max = 2000, message = "O campo 'biography' deve ter entre 10 e 2000 caracteres")
  private String biography;

  @NotNull(message = "O campo 'birthdate' é obrigatório")
  @Past(message = "O campo 'birthdate' deve ser uma data no passado")
  private LocalDate birthdate;

  @NotNull(message = "O campo 'genderId' é obrigatório")
  @Positive(message = "O campo 'genderId' deve ser um número válido")
  private Long genderId;

  @Size(min = 3, max = 50, message = "O campo 'otherGender' deve ter entre 3 e 13 caracteres")
  private String otherGender;

  @NotNull(message = "O campo 'phoneNumber' é obrigatório")
  @NotBlank(message = "O campo 'phoneNumber' não pode estar em branco")
  @Size(min = 13, max = 13, message = "O campo 'phoneNumber' deve conter exatamente 13 caracteres")
  private String phoneNumber;

  @Size(max = 255, message = "O campo 'profileImage' deve ter no máximo 255 caracteres")
  private String profileImageUrl;
}
