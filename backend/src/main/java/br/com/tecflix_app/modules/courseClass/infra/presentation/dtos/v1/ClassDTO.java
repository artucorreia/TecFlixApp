package br.com.tecflix_app.modules.courseClass.infra.presentation.dtos.v1;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClassDTO extends RepresentationModel<ClassDTO> {
  private UUID id;
  private String title;
  private String videoPath;
  private Boolean active;
  private LocalDateTime created_at;
}
