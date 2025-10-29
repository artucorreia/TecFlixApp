package br.com.tecflix_app.modules.review.infra.presentation.mapper;

import br.com.tecflix_app.modules.review.application.domain.entity.Review;
import br.com.tecflix_app.modules.review.infra.presentation.dtos.v1.CreateReviewDTO;
import br.com.tecflix_app.modules.review.infra.presentation.dtos.v1.ReviewResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ReviewPresentationMapper {
  ReviewResponseDTO toResponseDTO(Review review);

  @Mappings(value = {
      @Mapping(target = "id", ignore = true),
      @Mapping(target = "user", ignore = true),
      @Mapping(target = "course", ignore = true),
      @Mapping(target = "createdAt", ignore = true),
      @Mapping(target = "createdBy", ignore = true),
      @Mapping(target = "updatedAt", ignore = true),
      @Mapping(target = "updatedBy", ignore = true),
      @Mapping(target = "deleted", ignore = true),
  })
  Review toDomain(CreateReviewDTO createReviewDTO);
}
