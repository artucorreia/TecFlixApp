package br.com.tecflix_app.modules.gender.application.gateways;

import br.com.tecflix_app.modules.gender.application.domain.entity.Gender;

import java.util.Optional;

public interface GenderRepositoryGateway {
  Optional<Gender> findById(Long id);
}
