package br.com.tecflix_app.modules.courseClass.infra.gateways.mapper;

import br.com.tecflix_app.modules.courseClass.application.domain.entity.Class;
import br.com.tecflix_app.modules.courseClass.infra.persistence.ClassEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClassGatewaysMapper {
  ClassEntity map(Class classCourse);

  Class map(ClassEntity classCourseEntity);
}
