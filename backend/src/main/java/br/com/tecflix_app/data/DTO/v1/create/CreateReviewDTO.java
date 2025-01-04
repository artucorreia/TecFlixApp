package br.com.tecflix_app.data.DTO.v1.create;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.tecflix_app.data.DTO.v1.response.CourseDTO;
import br.com.tecflix_app.data.DTO.v1.response.UserDTO;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class CreateReviewDTO {
    
    @NotNull 
    @Positive 
    @Min(value = 1) @Max(value = 5)
    private Integer score;

    @Size(max = 255)
    private String comment;

    @JsonIgnore
    private UserDTO user;
    
    @JsonIgnore
    private CourseDTO course;
    
    @JsonIgnore
    private LocalDateTime createdAt = LocalDateTime.now();
}
