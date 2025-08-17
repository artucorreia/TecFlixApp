package br.com.tecflix_app.modules.social.application.usecases;

import br.com.tecflix_app.modules.social.application.domain.entity.Social;
import br.com.tecflix_app.modules.social.application.gateways.SocialGateway;
import br.com.tecflix_app.modules.user.application.domain.entity.User;
import java.util.List;
import java.util.logging.Logger;

public class CreateAllSocialsCaseImpl implements CreateAllSocialsCase {
  private final Logger LOGGER = Logger.getLogger(CreateAllSocialsCaseImpl.class.getName());
  private final SocialGateway socialGateway;

  public CreateAllSocialsCaseImpl(SocialGateway socialGateway) {
    this.socialGateway = socialGateway;
  }

  @Override
  public void execute(User user, List<Social> socials) {
    LOGGER.info("Saving professor's social");
    for (Social social : socials) {
      social.setUser(user);
      social.setUrl(social.getUrl().trim());
    }
    socialGateway.saveAll(socials);
  }
}
