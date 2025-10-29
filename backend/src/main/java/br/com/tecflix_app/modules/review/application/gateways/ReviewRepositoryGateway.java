package br.com.tecflix_app.modules.review.application.gateways;

import br.com.tecflix_app.modules.review.application.domain.entity.Review;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReviewRepositoryGateway {
  List<Review> findByCourseId(UUID courseId);

  Optional<Review> findByCourseIdAndUserId(UUID courseId, UUID userId);

  void save(Review review);
}
