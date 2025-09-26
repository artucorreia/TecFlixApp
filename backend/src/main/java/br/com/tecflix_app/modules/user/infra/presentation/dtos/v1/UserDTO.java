package br.com.tecflix_app.modules.user.infra.presentation.dtos.v1;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import br.com.tecflix_app.modules.professorData.infra.presentation.dtos.v1.ProfessorDataDTO;
import br.com.tecflix_app.modules.social.infra.presentation.dtos.v1.SocialResponseDTO;
import br.com.tecflix_app.modules.course.infra.presentation.dtos.v1.CourseResponseDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
  private UUID id;
  private String name;
  private String email;
//  private Role role;
  private LocalDateTime createdAt;
  private Boolean active;
  private List<CourseResponseDTO> enrolledCourses;
  private ProfessorDataDTO professorData;
  private List<SocialResponseDTO> socials;
  private List<CourseResponseDTO> coursesTaught;
//   private List<Payment> payments;
}
