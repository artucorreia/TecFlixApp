package br.com.tecflix_app.modules.occupation.application.gateways;

import br.com.tecflix_app.modules.occupation.application.domain.entity.Occupation;

import java.util.Optional;

public interface OccupationRepositoryGateway {
  Optional<Occupation> findById(Long id);
}
