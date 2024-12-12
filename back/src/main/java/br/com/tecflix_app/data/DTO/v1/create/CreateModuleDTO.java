package br.com.tecflix_app.data.DTO.v1.create;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.tecflix_app.data.DTO.v1.response.CourseDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class CreateModuleDTO {
    @NotNull @NotBlank @Size(min = 5, max = 40)
    private String title;
    
    @NotNull
    private CourseDTO course;

    @JsonIgnore
    private Boolean active = true;
    
    @JsonIgnore
    private LocalDateTime createdAt = LocalDateTime.now();
}
