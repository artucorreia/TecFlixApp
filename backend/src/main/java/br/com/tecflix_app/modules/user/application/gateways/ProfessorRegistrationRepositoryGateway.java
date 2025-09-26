package br.com.tecflix_app.modules.user.application.gateways;

import br.com.tecflix_app.modules.professorData.application.domain.entity.ProfessorData;
import br.com.tecflix_app.modules.social.application.domain.entity.Social;
import br.com.tecflix_app.modules.user.application.domain.entity.User;

import java.util.List;

public interface ProfessorRegistrationRepositoryGateway {
  void save(User user, ProfessorData professorData, List<Social> socials);
}
