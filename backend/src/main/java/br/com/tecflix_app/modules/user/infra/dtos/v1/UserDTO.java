package br.com.tecflix_app.modules.user.infra.dtos.v1;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import br.com.tecflix_app.modules.professorData.infra.dtos.v1.ProfessorDataDTO;
import br.com.tecflix_app.modules.social.infra.dtos.v1.response.SocialResponse;
import br.com.tecflix_app.modules.course.infra.dtos.v1.CourseDTO;
import br.com.tecflix_app.modules.user.application.domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserDTO {
  private UUID id;
  private String name;
  private String email;
  private Role role;
  private LocalDateTime createdAt;
  private Boolean active;
  private List<CourseDTO> enrolledCourses;
  private ProfessorDataDTO professorData;
  private List<SocialResponse> socials;
  private List<CourseDTO> coursesTaught;
  // private List<Payment> payments;
}
