package br.com.tecflix_app.modules.review.infra.presentation.dtos.v1;

import java.time.LocalDateTime;

import br.com.tecflix_app.modules.user.infra.presentation.dtos.v1.UserDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ReviewResponseDTO {
  private Long id;
  private Integer score;
  private String comment;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private UserDTO user;
}
