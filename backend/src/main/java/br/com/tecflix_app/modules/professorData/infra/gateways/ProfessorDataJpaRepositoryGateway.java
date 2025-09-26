package br.com.tecflix_app.modules.professorData.infra.gateways;

import br.com.tecflix_app.modules.professorData.application.domain.entity.ProfessorData;
import br.com.tecflix_app.modules.professorData.application.gateways.ProfessorDataRepositoryGateway;
import br.com.tecflix_app.modules.professorData.infra.gateways.mapper.ProfessorDataGatewaysMapper;
import br.com.tecflix_app.modules.professorData.infra.persistence.ProfessorDataEntity;
import br.com.tecflix_app.modules.professorData.infra.persistence.ProfessorDataRepository;
import br.com.tecflix_app.modules.professorData.infra.persistence.projection.ProfessorDataUserProfileProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProfessorDataJpaRepositoryGateway implements ProfessorDataRepositoryGateway {
  private final ProfessorDataRepository professorDataRepository;
  private final ProfessorDataGatewaysMapper professorDataGatewaysMapper;

  @Override
  public Optional<ProfessorData> findByUserId(UUID userId) {
    return professorDataRepository.findByUserId(userId).map(professorDataGatewaysMapper::map);
  }

  @Override
  public Optional<ProfessorData> findByPhoneNumber(String phoneNumber) {
    return professorDataRepository
        .findByPhoneNumber(phoneNumber)
        .map(professorDataGatewaysMapper::map);
  }

  @Override
  public Optional<ProfessorData> findProfileByUserId(UUID userId) {
    Optional<ProfessorDataEntity> optionalProfessorDataEntity =
        professorDataRepository
            .findProfessorDataUserProfileProjectionByUserId(userId)
            .map(professorDataGatewaysMapper::map);
    return optionalProfessorDataEntity.map(professorDataGatewaysMapper::map);
  }

  @Override
  public void save(ProfessorData professorData) {
    ProfessorDataEntity professorDataEntity = professorDataGatewaysMapper.map(professorData);
    professorDataRepository.save(professorDataEntity);
  }
}
