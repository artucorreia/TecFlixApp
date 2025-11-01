package br.com.tecflix_app.modules.auth.infra.gateways;

import br.com.tecflix_app.modules.auth.application.gateways.AuthenticatedUserGateway;
import br.com.tecflix_app.modules.auth.infra.gateways.mapper.AuthGatewaysMapper;
import br.com.tecflix_app.modules.user.application.domain.entity.User;
import br.com.tecflix_app.modules.user.infra.persistence.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

@Component
@RequiredArgsConstructor
public class AuthenticatedUserGatewayImpl implements AuthenticatedUserGateway {
  private final Logger LOGGER = Logger.getLogger(AuthenticatedUserGatewayImpl.class.getName());
  private final AuthGatewaysMapper authGatewaysMapper;

  @Override
  public Optional<UUID> findId() {
    LOGGER.info("Find authenticated user's id");
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null && authentication.isAuthenticated()) {
      UserEntity principal = (UserEntity) authentication.getPrincipal();
      return Optional.of(principal.getId());
    }
    return Optional.empty();
  }

  @Override
  public Optional<User> findUser() {
    LOGGER.info("Find authenticated user");
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null && authentication.isAuthenticated()) {
      UserEntity principal = (UserEntity) authentication.getPrincipal();
      User user = authGatewaysMapper.map(principal);
      return Optional.of(user);
    }
    return Optional.empty();
  }
}
