package br.com.tecflix_app.modules.module.infra.presentation.dtos.v1;

import java.time.LocalDateTime;
import java.util.Set;

import br.com.tecflix_app.modules.courseClass.infra.presentation.dtos.v1.ClassDTO;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ModuleResponseDTO {
  private Long id;
  private String title;
  private Boolean deleted;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private Set<ClassDTO> classes;
}
