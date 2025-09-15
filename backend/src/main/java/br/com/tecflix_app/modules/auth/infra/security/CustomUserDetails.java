package br.com.tecflix_app.modules.auth.infra.security;

import br.com.tecflix_app.modules.user.infra.persistence.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CustomUserDetails implements UserDetails {

  private UserEntity user;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    String userRole = "ROLE_" + user.getRole();
    return List.of(new SimpleGrantedAuthority(userRole));
  }

  @Override
  public String getPassword() {
    return user.getPassword();
  }

  @Override
  public String getUsername() {
    return user.getEmail();
  }
}
