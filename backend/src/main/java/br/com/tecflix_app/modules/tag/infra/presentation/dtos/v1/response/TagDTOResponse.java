package br.com.tecflix_app.modules.tag.infra.presentation.dtos.v1.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TagDTOResponse {
  private Long id;
  private String name;
  private Boolean active;
  private LocalDateTime createdAt;
}
