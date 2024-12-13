package br.com.tecflix_app.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.tecflix_app.model.Review;
import br.com.tecflix_app.projection.ReviewProjection;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>{
    List<ReviewProjection> findByCourseId(UUID courseId);
    
    @Query("SELECT r.id FROM Review r WHERE r.course.id = :courseId AND r.user.id = :userId")
    Optional<Long> findIdByCourseIdAndUserId(UUID courseId, UUID userId);
}
