package br.com.tecflix_app.modules.role.application.gateways;

import br.com.tecflix_app.modules.role.application.domain.entity.Role;

import java.util.Collection;
import java.util.List;

public interface RoleRepositoryGateway {
  List<Role> findByNameInIgnoreCase(Collection<String> names);
}
