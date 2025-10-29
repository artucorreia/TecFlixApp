package br.com.tecflix_app.modules.review.infra.gateways;

import br.com.tecflix_app.modules.review.application.domain.entity.Review;
import br.com.tecflix_app.modules.review.application.gateways.ReviewRepositoryGateway;
import br.com.tecflix_app.modules.review.infra.gateways.mapper.ReviewGatewaysMapper;
import br.com.tecflix_app.modules.review.infra.persistence.ReviewEntity;
import br.com.tecflix_app.modules.review.infra.persistence.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ReviewJpaRepositoryGateway implements ReviewRepositoryGateway {

  private final ReviewRepository reviewRepository;
  private final ReviewGatewaysMapper reviewGatewaysMapper;

  @Override
  public List<Review> findByCourseId(UUID courseId) {
    List<ReviewEntity> reviewEntities =
        reviewRepository.findByCourseIdAndDeletedFalse(courseId).stream()
            .map(reviewGatewaysMapper::toEntity)
            .toList();

    return reviewEntities.stream().map(reviewGatewaysMapper::toDomain).toList();
  }
}
