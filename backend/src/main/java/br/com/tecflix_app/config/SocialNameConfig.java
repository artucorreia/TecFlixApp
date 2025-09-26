package br.com.tecflix_app.config;

import br.com.tecflix_app.modules.socialName.application.gateways.SocialNameRepositoryGateway;
import br.com.tecflix_app.modules.socialName.application.usecases.FindSocialNameByIdUseCase;
import br.com.tecflix_app.modules.socialName.application.usecases.FindSocialNameByIdUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SocialNameConfig {
  @Bean
  public FindSocialNameByIdUseCase findSocialNameByIdUseCase(
      SocialNameRepositoryGateway socialNameRepositoryGateway) {
    return new FindSocialNameByIdUseCaseImpl(socialNameRepositoryGateway);
  }
}
