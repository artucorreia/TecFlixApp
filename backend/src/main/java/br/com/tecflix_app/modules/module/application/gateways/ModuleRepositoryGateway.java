package br.com.tecflix_app.modules.module.application.gateways;

import br.com.tecflix_app.modules.module.application.domain.entity.Module;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ModuleRepositoryGateway {
  Optional<Module> findById(Long id);

  List<Module> findByCourseId(UUID courseId);

  void save(Module module);
}
