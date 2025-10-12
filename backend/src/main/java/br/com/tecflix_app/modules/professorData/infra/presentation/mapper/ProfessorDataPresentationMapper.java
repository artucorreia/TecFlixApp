package br.com.tecflix_app.modules.professorData.infra.presentation.mapper;

import br.com.tecflix_app.modules.professorData.application.domain.entity.ProfessorData;
import br.com.tecflix_app.modules.professorData.infra.presentation.dtos.v1.AuthenticatedUserProfessorDataResponseDTO;
import br.com.tecflix_app.modules.professorData.infra.presentation.dtos.v1.CreateProfessorDataDTO;
import br.com.tecflix_app.modules.professorData.infra.presentation.dtos.v1.ProfessorDataUserProfileResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ProfessorDataPresentationMapper {

  @Mappings(
      value = {
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "user", ignore = true),
        @Mapping(target = "createdBy", ignore = true),
        @Mapping(target = "createdAt", ignore = true),
        @Mapping(target = "updatedBy", ignore = true),
        @Mapping(target = "updatedAt", ignore = true),
        @Mapping(target = "deleted", ignore = true),
        @Mapping(target = "occupation.id", source = "occupationId"),
        @Mapping(target = "gender.id", source = "genderId"),
      })
  ProfessorData map(CreateProfessorDataDTO createProfessorDataDTO);

  @Mappings(
      value = {
        @Mapping(target = "occupationName", source = "occupation.name"),
        @Mapping(target = "genderName", source = "gender.name")
      })
  AuthenticatedUserProfessorDataResponseDTO toAuthenticatedUserProfessorDataResponseDTO(
      ProfessorData professorData);

  @Mappings(
      value = {
        @Mapping(target = "professorId", source = "user.id"),
        @Mapping(target = "professorName", source = "user.name"),
        @Mapping(target = "occupationName", source = "occupation.name"),
        @Mapping(target = "genderName", source = "gender.name")
      })
  ProfessorDataUserProfileResponseDTO map(ProfessorData professorData);
}
