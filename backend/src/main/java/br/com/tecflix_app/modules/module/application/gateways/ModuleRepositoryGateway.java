package br.com.tecflix_app.modules.module.application.gateways;

import br.com.tecflix_app.modules.module.application.domain.entity.Module;

public interface ModuleRepositoryGateway {
  void save(Module module);
}
