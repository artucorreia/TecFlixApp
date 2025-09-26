package br.com.tecflix_app.modules.review.infra.dtos.v1;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.tecflix_app.modules.course.infra.presentation.dtos.v1.CourseResponseDTO;
import br.com.tecflix_app.modules.user.infra.presentation.dtos.v1.UserDTO;
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
@Setter
@Getter
public class CreateReviewDTO {

  @NotNull(message = "O campo 'score' é obrigatório")
  @Positive(message = "O campo 'score' deve ser um número positivo")
  @Min(value = 1, message = "O campo 'score' deve ter valor mínimo de 1")
  @Max(value = 5, message = "O campo 'score' deve ter valor máximo de 5")
  private Integer score;

  @Size(min = 10, max = 255, message = "O campo 'comment' deve ter entre 10 e 255 caracteres")
  private String comment;

  @JsonIgnore private UserDTO user;

  @JsonIgnore private CourseResponseDTO course;

  @JsonIgnore private LocalDateTime createdAt = LocalDateTime.now();
}
