package br.com.tecflix_app.audit;

import br.com.tecflix_app.modules.user.infra.persistence.UserEntity;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;
import java.util.UUID;

public class SpringSecurityAuditorAware implements AuditorAware<UUID> {
  @Override
  public Optional<UUID> getCurrentAuditor() {
    return Optional.ofNullable(SecurityContextHolder.getContext())
        .map(SecurityContext::getAuthentication)
        .filter(Authentication::isAuthenticated)
        .map(Authentication::getPrincipal)
        .filter(principal -> principal instanceof UserEntity)
        .map(UserEntity.class::cast)
        .map(UserEntity::getId);
  }
}
