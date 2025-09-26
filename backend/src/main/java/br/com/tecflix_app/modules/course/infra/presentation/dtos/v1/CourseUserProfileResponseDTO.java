package br.com.tecflix_app.modules.course.infra.presentation.dtos.v1;

import br.com.tecflix_app.modules.course.infra.persistence.projections.CourseUserProfileProjection;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseUserProfileResponseDTO {
  private UUID id;

  private String title;

  private String capeImageUrl;

  private Long totalScore;

  private Long totalReviews;

  private Double averageScore;

  private UUID professorId;

  private String professorName;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;
}
