package br.com.tecflix_app.modules.social.application.usecases;

import br.com.tecflix_app.modules.social.application.domain.entity.Social;
import br.com.tecflix_app.modules.socialName.application.domain.entity.SocialName;
import br.com.tecflix_app.modules.socialName.application.usecases.FindSocialNameByIdUseCase;
import br.com.tecflix_app.modules.user.application.domain.entity.User;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.logging.Logger;

public class PrepareAllSocialsForCreationUseCaseImpl implements PrepareAllSocialsForCreationUseCase {
  private final Logger LOGGER = Logger.getLogger(PrepareAllSocialsForCreationUseCaseImpl.class.getName());
  private final FindSocialNameByIdUseCase findSocialNameByIdUseCase;

  public PrepareAllSocialsForCreationUseCaseImpl(FindSocialNameByIdUseCase findSocialNameByIdUseCase) {
    this.findSocialNameByIdUseCase = findSocialNameByIdUseCase;
  }

  @Override
  public Set<Social> execute(User user, Set<Social> socials) {
    LOGGER.info("Preparing professor's socials");
    for (Social social : socials) {
      SocialName socialName = findSocialNameByIdUseCase.execute(social.getSocialName().getId());
      social.setSocialName(socialName);
      social.setUser(user);
      social.setUrl(social.getUrl().trim());
      social.setCreatedAt(LocalDateTime.now());
      social.setDeleted(false);
    }
    return socials;
  }
}
