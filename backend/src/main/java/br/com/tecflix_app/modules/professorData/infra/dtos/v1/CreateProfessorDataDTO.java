package br.com.tecflix_app.modules.professorData.infra.dtos.v1;

import java.time.LocalDate;
import java.time.LocalDateTime;

import br.com.tecflix_app.modules.professorData.application.domain.enums.Gender;
import br.com.tecflix_app.modules.professorData.application.domain.enums.Occupation;
import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.tecflix_app.modules.user.infra.dtos.v1.UserDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateProfessorDataDTO {
  @JsonIgnore private UserDTO user;

  @NotNull(message = "O campo 'cpf' é obrigatório")
  @NotBlank(message = "O campo 'cpf' não pode estar em branco")
  @Size(min = 11, max = 11, message = "O campo 'cpf' deve conter exatamente 11 caracteres")
  private String cpf;

  @NotNull(message = "O campo 'birthdate' é obrigatório")
  @Past(message = "O campo 'birthdate' deve ser uma data no passado")
  private LocalDate birthdate;

  @NotNull(message = "O campo 'gender' é obrigatório")
  private Gender gender;

  @NotNull(message = "O campo 'contact' é obrigatório")
  @NotBlank(message = "O campo 'contact' não pode estar em branco")
  @Size(min = 13, max = 13, message = "O campo 'contact' deve conter exatamente 13 caracteres")
  private String contact;

  @NotNull(message = "O campo 'occupation' é obrigatório")
  private Occupation occupation;

  @NotNull(message = "O campo 'biography' é obrigatório")
  @NotBlank(message = "O campo 'biography' não pode estar em branco")
  @Size(min = 10, max = 1000, message = "O campo 'biography' deve ter entre 10 e 1000 caracteres")
  private String biography;

  @Size(max = 255, message = "O campo 'profileImage' deve ter no máximo 255 caracteres")
  private String profileImage;

  @JsonIgnore private LocalDateTime createdAt;
}
