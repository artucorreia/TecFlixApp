package br.com.tecflix_app.modules.role.infra.gateways;

import br.com.tecflix_app.modules.role.application.domain.entity.Role;
import br.com.tecflix_app.modules.role.application.gateways.RoleRepositoryGateway;
import br.com.tecflix_app.modules.role.infra.gateways.mapper.RoleGatewaysMapper;
import br.com.tecflix_app.modules.role.infra.persistence.RoleEntity;
import br.com.tecflix_app.modules.role.infra.persistence.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RoleJpaRepositoryGateway implements RoleRepositoryGateway {
  private final RoleRepository roleRepository;
  private final RoleGatewaysMapper roleGatewaysMapper;

  @Override
  public List<Role> findByNameInIgnoreCase(Collection<String> names) {
    List<RoleEntity> roleEntities = roleRepository.findByNameInIgnoreCase(names);
    return roleEntities.stream().map(roleGatewaysMapper::map).toList();
  }
}
