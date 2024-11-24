package br.com.tecflix_app.data.DTO.v1.create;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter @Getter
public class RegisterProfessorDTO {
    @NotNull
    @Valid
    private CreateProfessorDataDTO professorData;
    
    @NotNull
    @Valid
    private CreateAddressDTO address;
    
    @NotNull
    @Valid
    private CreateBackDataDTO bankData;
    
    @Valid
    private List<CreateSocialDTO> socials;

    @JsonIgnore
    private LocalDateTime createdAt = LocalDateTime.now();
}
