package br.com.tecflix_app.modules.module.infra.gateways.mapper;

import br.com.tecflix_app.modules.course.infra.persistence.projections.CourseDetailsProjection;
import br.com.tecflix_app.modules.module.application.domain.entity.Module;
import br.com.tecflix_app.modules.module.infra.persistence.ModuleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ModuleGatewaysMapper {

  @Named("moduleDetailsProjectionToModule")
  @Mappings(value = {
      @Mapping(target = "active", ignore = true),
      @Mapping(target = "createdAt", ignore = true),
      @Mapping(target = "course", ignore = true)
  })
  Module detailsProjectionToDomain(CourseDetailsProjection.ModuleCourseDetailsProjection module);

  ModuleEntity domainToEntity(Module module);
}
