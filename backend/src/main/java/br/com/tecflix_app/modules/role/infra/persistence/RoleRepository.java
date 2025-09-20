package br.com.tecflix_app.modules.role.infra.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
  List<RoleEntity> findByNameInIgnoreCase(Collection<String> names);
}
