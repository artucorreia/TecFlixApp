package br.com.tecflix_app.config;

import br.com.tecflix_app.modules.social.application.gateways.SocialRepositoryGateway;
import br.com.tecflix_app.modules.social.application.usecases.PrepareAllSocialsForCreationUseCase;
import br.com.tecflix_app.modules.social.application.usecases.PrepareAllSocialsForCreationUseCaseImpl;
import br.com.tecflix_app.modules.social.application.usecases.FindSocialsProfileByUserIdUseCase;
import br.com.tecflix_app.modules.social.application.usecases.FindSocialsProfileByUserIdUseCaseImpl;
import br.com.tecflix_app.modules.socialName.application.usecases.FindSocialNameByIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SocialConfig {
  @Bean
  public FindSocialsProfileByUserIdUseCase findSocialsProfileByUserIdUseCase(
      SocialRepositoryGateway socialRepositoryGateway) {
    return new FindSocialsProfileByUserIdUseCaseImpl(socialRepositoryGateway);
  }

  @Bean
  public PrepareAllSocialsForCreationUseCase createAllSocialsCase(
      FindSocialNameByIdUseCase findSocialNameByIdUseCase) {
    return new PrepareAllSocialsForCreationUseCaseImpl(findSocialNameByIdUseCase);
  }
}
