package br.com.tecflix_app.modules.professorData.application.gateways;

import br.com.tecflix_app.modules.professorData.application.domain.entity.ProfessorData;

import java.util.Optional;
import java.util.UUID;

public interface ProfessorDataRepositoryGateway {
  Optional<ProfessorData> findByUserId(UUID userId);

  Optional<ProfessorData> findByPhoneNumber(String phoneNumber);

  Optional<ProfessorData> findProfileByUserId(UUID userId);

  Optional<ProfessorData> findAuthenticatedByUserId(UUID userId);

  void save(ProfessorData professorData);
}
