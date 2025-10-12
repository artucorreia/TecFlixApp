package br.com.tecflix_app.modules.social.application.usecases;

import br.com.tecflix_app.modules.social.application.domain.entity.Social;
import br.com.tecflix_app.modules.social.application.gateways.SocialRepositoryGateway;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

public class FindSocialsAuthenticatedUserByUserIdUseCaseImpl
    implements FindSocialsAuthenticatedUserByUserIdUseCase {
  private final Logger LOGGER =
      Logger.getLogger(FindSocialsAuthenticatedUserByUserIdUseCaseImpl.class.getName());
  private final SocialRepositoryGateway socialRepositoryGateway;

  public FindSocialsAuthenticatedUserByUserIdUseCaseImpl(
      SocialRepositoryGateway socialRepositoryGateway) {
    this.socialRepositoryGateway = socialRepositoryGateway;
  }

  @Override
  public List<Social> execute(UUID userId) {
    LOGGER.info("Finding socials for user's settings by user id: " + userId);
    return socialRepositoryGateway.findAuthenticatedByUserId(userId);
  }
}
