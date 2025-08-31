package br.com.tecflix_app.modules.module.application.gateways;

import br.com.tecflix_app.modules.module.application.domain.entity.Module;

import java.util.Optional;

public interface ModuleRepositoryGateway {
  Optional<Module> findById(Long id);

  void save(Module module);
}
