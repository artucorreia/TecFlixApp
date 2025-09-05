package br.com.tecflix_app.modules.courseClass.infra.gateways;

import br.com.tecflix_app.modules.courseClass.application.domain.entity.Class;
import br.com.tecflix_app.modules.courseClass.application.gateways.ClassRepositoryGateway;
import br.com.tecflix_app.modules.courseClass.infra.gateways.mapper.ClassGatewaysMapper;
import br.com.tecflix_app.modules.courseClass.infra.persistence.ClassEntity;
import br.com.tecflix_app.modules.courseClass.infra.persistence.ClassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClassJpaRepositoryGateway implements ClassRepositoryGateway {
  private final ClassRepository classRepository;
  private final ClassGatewaysMapper classGatewaysMapper;

  @Override
  public void save(Class courseClass) {
    ClassEntity classEntity = classGatewaysMapper.domainToEntity(courseClass);
    classRepository.save(classEntity);
  }
}