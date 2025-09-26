package br.com.tecflix_app.modules.module.infra.gateways;

import br.com.tecflix_app.modules.module.application.domain.entity.Module;
import br.com.tecflix_app.modules.module.application.gateways.ModuleRepositoryGateway;
import br.com.tecflix_app.modules.module.infra.gateways.mapper.ModuleGatewaysMapper;
import br.com.tecflix_app.modules.module.infra.persistence.ModuleEntity;
import br.com.tecflix_app.modules.module.infra.persistence.ModuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ModuleJpaRepositoryGateway implements ModuleRepositoryGateway {
  private final ModuleRepository moduleRepository;
  private final ModuleGatewaysMapper moduleGatewaysMapper;

  @Override
  public Optional<Module> findById(Long id) {
    Optional<ModuleEntity> moduleEntityOptional = moduleRepository.findById(id);
    return moduleEntityOptional.map(moduleGatewaysMapper::map);
  }

  @Override
  public List<Module> findByCourseId(UUID courseId) {
    List<ModuleEntity> moduleEntities = moduleRepository.findByCourseId(courseId);
    return moduleEntities.stream().map(moduleGatewaysMapper::map).toList();
  }

  @Override
  public void save(Module module) {
    ModuleEntity moduleEntity = moduleGatewaysMapper.map(module);
    moduleRepository.save(moduleEntity);
  }
}
