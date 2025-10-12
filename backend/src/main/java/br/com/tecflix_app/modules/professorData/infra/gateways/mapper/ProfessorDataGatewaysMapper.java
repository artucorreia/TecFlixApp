package br.com.tecflix_app.modules.professorData.infra.gateways.mapper;

import br.com.tecflix_app.modules.gender.infra.persistence.GenderEntity;
import br.com.tecflix_app.modules.occupation.infra.persistence.OccupationEntity;
import br.com.tecflix_app.modules.professorData.application.domain.entity.ProfessorData;
import br.com.tecflix_app.modules.professorData.infra.persistence.ProfessorDataEntity;
import br.com.tecflix_app.modules.professorData.infra.persistence.projection.AuthenticatedUserProfessorDataProjection;
import br.com.tecflix_app.modules.professorData.infra.persistence.projection.ProfessorDataUserProfileProjection;
import br.com.tecflix_app.modules.user.infra.persistence.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfessorDataGatewaysMapper {
  ProfessorData map(ProfessorDataEntity professorDataEntity);

  ProfessorDataEntity map(ProfessorData professorDataEntity);

  ProfessorDataEntity map(ProfessorDataUserProfileProjection professorDataUserProfileProjection);

  ProfessorDataEntity map(
      AuthenticatedUserProfessorDataProjection authenticatedUserProfessorDataProjection);

  OccupationEntity map(AuthenticatedUserProfessorDataProjection.OccupationProjection projection);

  GenderEntity map(AuthenticatedUserProfessorDataProjection.GenderProjection projection);

  UserEntity map(ProfessorDataUserProfileProjection.UserProjection projection);

  OccupationEntity map(ProfessorDataUserProfileProjection.OccupationProjection projection);

  GenderEntity map(ProfessorDataUserProfileProjection.GenderProjection projection);
}
