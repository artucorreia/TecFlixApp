package br.com.tecflix_app.modules.courseClass.application.gateways;

import br.com.tecflix_app.modules.courseClass.application.domain.entity.Class;

public interface ClassRepositoryGateway {
  void save(Class courseClass);
}
