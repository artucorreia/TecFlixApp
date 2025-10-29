package br.com.tecflix_app.modules.review.infra.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.tecflix_app.modules.review.infra.persistence.projections.ReviewProjection;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
  List<ReviewProjection> findByCourseIdAndDeletedFalse(UUID courseId);

  Optional<ReviewEntity> findByCourseIdAndUserIdAndDeletedFalse(UUID courseId, UUID userId);
}
