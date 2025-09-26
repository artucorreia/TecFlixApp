package br.com.tecflix_app.modules.social.application.usecases;

import br.com.tecflix_app.modules.social.application.domain.entity.Social;
import br.com.tecflix_app.modules.social.application.gateways.SocialRepositoryGateway;
import br.com.tecflix_app.modules.user.application.domain.entity.User;
import java.util.logging.Logger;

public class CreateSocialUseCaseImpl implements CreateSocialUseCase {
  private final Logger LOGGER = Logger.getLogger(CreateSocialUseCaseImpl.class.getName());

  private final SocialRepositoryGateway socialRepositoryGateway;

  public CreateSocialUseCaseImpl(SocialRepositoryGateway socialRepositoryGateway) {
    this.socialRepositoryGateway = socialRepositoryGateway;
  }

  @Override
  public void execute(User user, Social social) {
    LOGGER.info("Saving professor's social");
    social.setUser(user);
    social.setUrl(social.getUrl().trim());
    socialRepositoryGateway.save(social);
  }
}
