package br.com.tecflix_app.modules.tag.infra.presentation.dtos.v1.response;

import java.time.LocalDateTime;

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
public class TagResponseDTO {
  private Long id;
  private String name;
  private Boolean active;
  private LocalDateTime createdAt;
}
