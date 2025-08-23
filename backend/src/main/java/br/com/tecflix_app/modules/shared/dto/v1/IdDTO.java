package br.com.tecflix_app.modules.shared.dto.v1;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IdDTO {
  @NotNull(message = "O id é obrigatório")
  private Long id;
}
