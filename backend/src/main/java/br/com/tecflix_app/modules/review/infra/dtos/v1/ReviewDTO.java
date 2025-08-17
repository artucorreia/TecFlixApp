package br.com.tecflix_app.modules.review.infra.dtos.v1;

import java.time.LocalDateTime;

import br.com.tecflix_app.modules.user.infra.dtos.v1.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ReviewDTO {
  private Long id;
  private Integer score;
  private String comment;
  private LocalDateTime createdAt;
  private UserDTO user;
}
