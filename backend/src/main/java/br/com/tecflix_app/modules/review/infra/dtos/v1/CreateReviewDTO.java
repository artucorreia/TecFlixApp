package br.com.tecflix_app.modules.review.infra.dtos.v1;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.tecflix_app.modules.course.infra.presentation.dtos.v1.CourseResponseDTO;
import br.com.tecflix_app.modules.user.infra.dtos.v1.UserDTO;
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

  @NotNull
  @Positive
  @Min(value = 1)
  @Max(value = 5)
  private Integer score;

  @Size(max = 255)
  private String comment;

  @JsonIgnore private UserDTO user;

  @JsonIgnore private CourseResponseDTO course;

  @JsonIgnore private LocalDateTime createdAt = LocalDateTime.now();
}
