package br.com.tecflix_app.modules.auth.infra.gateways;

import br.com.tecflix_app.modules.auth.application.gateways.AuthenticatedUserGateway;
import br.com.tecflix_app.modules.user.infra.persistence.UserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class AuthenticatedUserGatewayImpl implements AuthenticatedUserGateway {

  @Override
  public Optional<UUID> findId() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null && authentication.isAuthenticated()) {
      UserEntity principal = (UserEntity) authentication.getPrincipal();
      return Optional.of(principal.getId());
    }
    return Optional.empty();
  }
}
