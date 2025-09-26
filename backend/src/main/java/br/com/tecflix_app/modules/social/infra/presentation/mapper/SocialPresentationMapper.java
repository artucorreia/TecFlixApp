package br.com.tecflix_app.modules.social.infra.presentation.mapper;

import br.com.tecflix_app.modules.social.application.domain.entity.Social;
import br.com.tecflix_app.modules.social.infra.presentation.dtos.v1.CreateSocialDTO;
import br.com.tecflix_app.modules.social.infra.presentation.dtos.v1.SocialUserProfileResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface SocialPresentationMapper {

  @Mappings(
      value = {
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "user", ignore = true),
        @Mapping(target = "createdAt", ignore = true),
        @Mapping(target = "createdBy", ignore = true),
        @Mapping(target = "updatedAt", ignore = true),
        @Mapping(target = "updatedBy", ignore = true),
        @Mapping(target = "deleted", ignore = true),
        @Mapping(target = "socialName.id", source = "socialNameId"),
      })
  Social map(CreateSocialDTO createSocialDTO);

  Set<Social> map(Set<CreateSocialDTO> createSocialDTOS);

  @Mappings(
      value = {
        @Mapping(target = "socialName", source = "socialName.name"),
      })
  SocialUserProfileResponseDTO map(Social social);

  List<SocialUserProfileResponseDTO> map(List<Social> social);
}
