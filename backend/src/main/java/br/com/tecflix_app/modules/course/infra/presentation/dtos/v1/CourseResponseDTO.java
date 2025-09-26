package br.com.tecflix_app.modules.course.infra.presentation.dtos.v1;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import br.com.tecflix_app.modules.module.infra.presentation.dtos.v1.ModuleResponseDTO;
import br.com.tecflix_app.modules.tag.infra.presentation.dtos.v1.response.TagResponseDTO;
import br.com.tecflix_app.modules.user.infra.presentation.dtos.v1.UserDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseResponseDTO extends RepresentationModel<CourseResponseDTO> {
  private UUID id;
  private String title;
  private String description;
  private String capeImage;
  private Boolean active;
  private LocalDateTime createdAt;
  private Long totalScore;
  private Long totalReviews;
  private Double averageScore;
  private UserDTO professor;
  private Set<ModuleResponseDTO> modules;
  private Set<TagResponseDTO> tags;
}
