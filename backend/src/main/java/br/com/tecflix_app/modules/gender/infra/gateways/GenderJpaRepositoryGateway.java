package br.com.tecflix_app.modules.gender.infra.gateways;

import br.com.tecflix_app.modules.gender.application.domain.entity.Gender;
import br.com.tecflix_app.modules.gender.application.gateways.GenderRepositoryGateway;
import br.com.tecflix_app.modules.gender.infra.gateways.mapper.GenderGatewaysMapper;
import br.com.tecflix_app.modules.gender.infra.persistence.GenderEntity;
import br.com.tecflix_app.modules.gender.infra.persistence.GenderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GenderJpaRepositoryGateway implements GenderRepositoryGateway {
  private final GenderRepository genderRepository;
  private final GenderGatewaysMapper genderGatewaysMapper;

  @Override
  public Optional<Gender> findById(Long id) {
    Optional<GenderEntity> optionalGenderEntity = genderRepository.findById(id);
    return optionalGenderEntity.map(genderGatewaysMapper::map);
  }
}
