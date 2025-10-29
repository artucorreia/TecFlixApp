package br.com.tecflix_app.modules.review.infra.gateways;

import br.com.tecflix_app.modules.review.application.domain.entity.Review;
import br.com.tecflix_app.modules.review.application.gateways.ReviewRepositoryGateway;
import br.com.tecflix_app.modules.review.infra.gateways.mapper.ReviewGatewaysMapper;
import br.com.tecflix_app.modules.review.infra.persistence.ReviewEntity;
import br.com.tecflix_app.modules.review.infra.persistence.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
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

  @Override
  public Optional<Review> findByCourseIdAndUserId(UUID courseId, UUID userId) {
    Optional<ReviewEntity> reviewEntityOptional =
        reviewRepository.findByCourseIdAndUserIdAndDeletedFalse(courseId, userId);
    return reviewEntityOptional.map(reviewGatewaysMapper::toDomain);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void save(Review review) {
    ReviewEntity reviewEntity = reviewGatewaysMapper.toEntity(review);
    reviewRepository.save(reviewEntity);
  }
}
