package br.com.tecflix_app.config;

import br.com.tecflix_app.modules.tag.application.usecases.FindAllTagsCase;
import br.com.tecflix_app.modules.tag.application.usecases.FindAllTagsCaseImpl;
import br.com.tecflix_app.modules.tag.application.usecases.FindTagByIdCase;
import br.com.tecflix_app.modules.tag.application.usecases.FindTagByIdCaseImpl;
import br.com.tecflix_app.modules.tag.infra.gateways.TagJpaRepositoryGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TagConfig {
  @Bean
  FindTagByIdCase findTagByIdCase(TagJpaRepositoryGateway tagJpaRepositoryGateway) {
    return new FindTagByIdCaseImpl(tagJpaRepositoryGateway);
  }

  @Bean
  FindAllTagsCase findAllTagsCase(TagJpaRepositoryGateway tagJpaRepositoryGateway) {
    return new FindAllTagsCaseImpl(tagJpaRepositoryGateway);
  }
}
