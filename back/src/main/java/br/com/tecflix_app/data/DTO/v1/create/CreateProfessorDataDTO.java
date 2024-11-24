package br.com.tecflix_app.data.DTO.v1.create;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.tecflix_app.data.DTO.v1.response.UserDTO;
import br.com.tecflix_app.model.enums.Gender;
import br.com.tecflix_app.model.enums.Occupation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class CreateProfessorDataDTO {
    @JsonIgnore
    private UserDTO user;
    
    @NotNull
    @NotBlank
    @Size(min = 11, max = 11)
    private String cpf;
    
    @NotNull
    private LocalDate birthdate;
    
    @NotNull
    private Gender gender;
    
    @NotNull
    @NotBlank
    @Size(min = 13, max = 13)
    private String contact;
    
    @NotNull
    private Occupation occupation;
    
    @NotNull
    @Size(min = 10, max = 1000)
    private String biography;
    
    @Size(max = 255)
    private String profileImage;
    
    @JsonIgnore
    private LocalDateTime createdAt;
}