package br.com.tecflix_app.modules.review.infra.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.tecflix_app.modules.review.infra.persistence.projections.ReviewProjection;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
  @Query("SELECT r.id FROM ReviewEntity r WHERE r.courseEntity.id = :courseEntityId")
  List<ReviewProjection> findByCourseEntityId(UUID courseEntityId);

  @Query("SELECT r.id FROM ReviewEntity r WHERE r.courseEntity.id = :courseEntityId AND r.userEntity.id = :userEntityId")
  Optional<Long> findIdByCourseIdAndUserId(UUID courseEntityId, UUID userEntityId);
}
