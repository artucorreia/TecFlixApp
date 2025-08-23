package br.com.tecflix_app.config;

import br.com.tecflix_app.modules.tag.application.gateways.TagRepositoryGateway;
import br.com.tecflix_app.modules.tag.application.usecases.FindAllTagsCase;
import br.com.tecflix_app.modules.tag.application.usecases.FindAllTagsCaseImpl;
import br.com.tecflix_app.modules.tag.application.usecases.FindTagByIdCase;
import br.com.tecflix_app.modules.tag.application.usecases.FindTagByIdCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TagConfig {
  @Bean
  FindTagByIdCase findTagByIdCase(TagRepositoryGateway tagRepositoryGateway) {
    return new FindTagByIdCaseImpl(tagRepositoryGateway);
  }

  @Bean
  FindAllTagsCase findAllTagsCase(TagRepositoryGateway tagRepositoryGateway) {
    return new FindAllTagsCaseImpl(tagRepositoryGateway);
  }
}
