package br.com.tecflix_app.modules.module.infra.gateways;

import br.com.tecflix_app.modules.module.application.domain.entity.Module;
import br.com.tecflix_app.modules.module.application.gateways.ModuleRepositoryGateway;
import br.com.tecflix_app.modules.module.infra.gateways.mapper.ModuleGatewaysMapper;
import br.com.tecflix_app.modules.module.infra.persistence.ModuleEntity;
import br.com.tecflix_app.modules.module.infra.persistence.ModuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ModuleJpaRepositoryGateway implements ModuleRepositoryGateway {
  private final ModuleRepository moduleRepository;
  private final ModuleGatewaysMapper moduleGatewaysMapper;

  @Override
  public Optional<Module> findById(Long id) {
    Optional<ModuleEntity> moduleEntityOptional = moduleRepository.findById(id);
    return moduleEntityOptional.map(moduleGatewaysMapper::entityToDomain);
  }

  @Override
  public void save(Module module) {
    ModuleEntity moduleEntity = moduleGatewaysMapper.domainToEntity(module);
    moduleRepository.save(moduleEntity);
  }
}
