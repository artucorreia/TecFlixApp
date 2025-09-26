package br.com.tecflix_app.modules.user.infra.gateways;

import br.com.tecflix_app.modules.professorData.application.domain.entity.ProfessorData;
import br.com.tecflix_app.modules.professorData.application.gateways.ProfessorDataRepositoryGateway;
import br.com.tecflix_app.modules.social.application.domain.entity.Social;
import br.com.tecflix_app.modules.social.application.gateways.SocialRepositoryGateway;
import br.com.tecflix_app.modules.user.application.domain.entity.User;
import br.com.tecflix_app.modules.user.application.gateways.ProfessorRegistrationRepositoryGateway;
import br.com.tecflix_app.modules.user.application.gateways.UserRepositoryGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProfessorRegistrationJpaRepositoryGateway
    implements ProfessorRegistrationRepositoryGateway {
  private final UserRepositoryGateway userRepositoryGateway;
  private final ProfessorDataRepositoryGateway professorDataRepositoryGateway;
  private final SocialRepositoryGateway socialRepositoryGateway;

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void save(User user, ProfessorData professorData, List<Social> socials) {
    userRepositoryGateway.save(user);
    professorDataRepositoryGateway.save(professorData);
    socialRepositoryGateway.saveAll(socials);
  }
}
