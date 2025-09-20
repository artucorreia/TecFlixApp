package br.com.tecflix_app.config.audit;

import br.com.tecflix_app.audit.SpringSecurityAuditorAware;
import br.com.tecflix_app.modules.user.infra.persistence.UserEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class AuditConfig {
  @Bean
  public AuditorAware<UserEntity> auditorProvider() {
    return new SpringSecurityAuditorAware();
  }
}
