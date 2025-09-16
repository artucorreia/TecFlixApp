package br.com.tecflix_app.modules.auth.infra.security.jwt;

import br.com.tecflix_app.modules.user.infra.persistence.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.tecflix_app.modules.user.infra.persistence.UserRepository;

import java.util.Optional;

@Service
public class AuthorizationService implements UserDetailsService {

  private final UserRepository repository;

  @Autowired
  public AuthorizationService(UserRepository repository) {
    this.repository = repository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserEntity userEntity =
        repository
            .findByEmail(username)
            .orElseThrow(
                () -> new UsernameNotFoundException("Nenhum usuário encontrado para este email"));
    return new CustomUserDetails(userEntity);
  }
}
