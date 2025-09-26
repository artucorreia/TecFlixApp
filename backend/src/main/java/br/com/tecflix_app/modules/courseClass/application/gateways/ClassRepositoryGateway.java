package br.com.tecflix_app.modules.courseClass.application.gateways;

import br.com.tecflix_app.modules.courseClass.application.domain.entity.Class;

import java.util.List;

public interface ClassRepositoryGateway {
  List<Class> findByModuleId(Long moduleId);

  void save(Class courseClass);
}
