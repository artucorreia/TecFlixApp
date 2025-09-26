package br.com.tecflix_app.modules.courseClass.infra.gateways;

import br.com.tecflix_app.modules.courseClass.application.domain.entity.Class;
import br.com.tecflix_app.modules.courseClass.application.gateways.ClassRepositoryGateway;
import br.com.tecflix_app.modules.courseClass.infra.gateways.mapper.ClassGatewaysMapper;
import br.com.tecflix_app.modules.courseClass.infra.persistence.ClassEntity;
import br.com.tecflix_app.modules.courseClass.infra.persistence.ClassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ClassJpaRepositoryGateway implements ClassRepositoryGateway {
  private final ClassRepository classRepository;
  private final ClassGatewaysMapper classGatewaysMapper;

  @Override
  public List<Class> findByModuleId(Long moduleId) {
    List<ClassEntity> classEntities = classRepository.findByModuleId(moduleId);
    return classEntities.stream().map(classGatewaysMapper::map).toList();
  }

  @Override
  public void save(Class courseClass) {
    ClassEntity classEntity = classGatewaysMapper.map(courseClass);
    classRepository.save(classEntity);
  }
}
