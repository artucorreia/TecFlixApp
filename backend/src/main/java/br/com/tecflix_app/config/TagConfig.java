package br.com.tecflix_app.config;

import br.com.tecflix_app.modules.tag.application.gateways.TagRepositoryGateway;
import br.com.tecflix_app.modules.tag.application.usecases.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TagConfig {
  @Bean
  public FindTagByIdUseCase findTagByIdCase(TagRepositoryGateway tagRepositoryGateway) {
    return new FindTagByIdUseCaseImpl(tagRepositoryGateway);
  }

  @Bean
  public FindAllTagsByIdUseCase findAllTagsByIdUseCase(TagRepositoryGateway tagRepositoryGateway) {
    return new FindAllTagsByIdUseCaseImpl(tagRepositoryGateway);
  }

  @Bean
  public FindAllTagsUseCase findAllTagsCase(TagRepositoryGateway tagRepositoryGateway) {
    return new FindAllTagsUseCaseImpl(tagRepositoryGateway);
  }
}
