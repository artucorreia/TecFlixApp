package br.com.tecflix_app.modules.review.infra.presentation.mapper;

import br.com.tecflix_app.modules.review.application.domain.entity.Review;
import br.com.tecflix_app.modules.review.infra.presentation.dtos.v1.ReviewResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReviewPresentationMapper {
  ReviewResponseDTO toResponseDTO(Review review);
}
