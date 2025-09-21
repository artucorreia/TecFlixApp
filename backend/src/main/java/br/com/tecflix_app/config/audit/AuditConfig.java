package br.com.tecflix_app.config.audit;

import br.com.tecflix_app.audit.SpringSecurityAuditorAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.UUID;

@Configuration
@EnableJpaAuditing
public class AuditConfig {
  @Bean
  public AuditorAware<UUID> auditorProvider() {
    return new SpringSecurityAuditorAware();
  }
}
