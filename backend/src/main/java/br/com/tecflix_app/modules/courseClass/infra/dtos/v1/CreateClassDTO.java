package br.com.tecflix_app.modules.courseClass.infra.dtos.v1;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.tecflix_app.modules.module.infra.dtos.v1.ModuleDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateClassDTO {
  @NotNull
  @NotBlank
  @Size(min = 5, max = 20)
  private String title;

  @NotNull
  @NotBlank
  @Size(max = 255)
  private String videoPath;

  @NotNull private ModuleDTO module;

  @JsonIgnore private Boolean active = true;

  @JsonIgnore private LocalDateTime createdAt = LocalDateTime.now();
}
