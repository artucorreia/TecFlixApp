package br.com.tecflix_app.modules.user.infra.presentation.dtos.v1;

import br.com.tecflix_app.modules.course.infra.presentation.dtos.v1.CourseUserProfileResponseDTO;
import br.com.tecflix_app.modules.professorData.infra.presentation.dtos.v1.ProfessorDataUserProfileResponseDTO;
import br.com.tecflix_app.modules.social.infra.presentation.dtos.v1.SocialUserProfileResponseDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfessorProfileResponseDTO {
  ProfessorDataUserProfileResponseDTO professorData;
  List<SocialUserProfileResponseDTO> socials;
  List<CourseUserProfileResponseDTO> courses;
}
