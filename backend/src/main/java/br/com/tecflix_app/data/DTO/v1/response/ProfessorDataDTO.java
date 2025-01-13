package br.com.tecflix_app.data.DTO.v1.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import br.com.tecflix_app.model.enums.Gender;
import br.com.tecflix_app.model.enums.Occupation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProfessorDataDTO {
    private UserDTO user;
    private String cpf;
    private LocalDate birthdate;
    private Gender gender;
    private String contact;
    private Occupation occupation;
    private String biography;
    private String profileImage;
    private LocalDateTime createdAt;
}