package br.com.tecflix_app.modules.review.infra.gateways.mapper;

import br.com.tecflix_app.modules.review.application.domain.entity.Review;
import br.com.tecflix_app.modules.review.infra.persistence.ReviewEntity;
import br.com.tecflix_app.modules.review.infra.persistence.projections.ReviewProjection;
import br.com.tecflix_app.modules.user.infra.persistence.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ReviewGatewaysMapper {
  @Mappings(value = {
      @Mapping(target = "createdBy", ignore = true),
      @Mapping(target = "updatedBy", ignore = true),
      @Mapping(target = "course", ignore = true)
  })
  ReviewEntity toEntity(ReviewProjection reviewProjection);

  ReviewEntity toEntity(Review review);

  UserEntity userProjectionToUserEntity(ReviewProjection.UserProjection userProjection);

  Review toDomain(ReviewEntity reviewEntity);
}
