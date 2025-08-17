package br.com.tecflix_app.modules.social.application.usecases;

import br.com.tecflix_app.modules.social.application.domain.entity.Social;
import br.com.tecflix_app.modules.social.application.gateways.SocialGateway;
import br.com.tecflix_app.modules.user.application.domain.entity.User;
import java.util.logging.Logger;

public class CreateSocialCaseImpl implements CreateSocialCase {
  private final Logger LOGGER = Logger.getLogger(CreateSocialCaseImpl.class.getName());

  private final SocialGateway socialGateway;

  public CreateSocialCaseImpl(SocialGateway socialGateway) {
    this.socialGateway = socialGateway;
  }

  @Override
  public void execute(User user, Social social) {
    LOGGER.info("Saving professor's social");
    social.setUser(user);
    social.setUrl(social.getUrl().trim());
    socialGateway.save(social);
  }
}
