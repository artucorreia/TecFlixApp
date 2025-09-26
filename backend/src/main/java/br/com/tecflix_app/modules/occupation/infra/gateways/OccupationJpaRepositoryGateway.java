package br.com.tecflix_app.modules.occupation.infra.gateways;

import br.com.tecflix_app.modules.occupation.application.domain.entity.Occupation;
import br.com.tecflix_app.modules.occupation.application.gateways.OccupationRepositoryGateway;
import br.com.tecflix_app.modules.occupation.infra.gateways.mapper.OccupationGatewaysMapper;
import br.com.tecflix_app.modules.occupation.infra.persistence.OccupationEntity;
import br.com.tecflix_app.modules.occupation.infra.persistence.OccupationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OccupationJpaRepositoryGateway implements OccupationRepositoryGateway {
  private final OccupationRepository occupationRepository;
  private final OccupationGatewaysMapper occupationGatewaysMapper;

  @Override
  public Optional<Occupation> findById(Long id) {
    Optional<OccupationEntity> optionalOccupationEntity = occupationRepository.findById(id);
    return optionalOccupationEntity.map(occupationGatewaysMapper::map);
  }
}
