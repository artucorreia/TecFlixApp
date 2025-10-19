package br.com.tecflix_app.modules.course.infra.persistence;

import br.com.tecflix_app.modules.course.infra.persistence.projections.CourseDetailsProjection;
import br.com.tecflix_app.modules.course.infra.persistence.projections.CourseProjection;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.com.tecflix_app.modules.course.infra.persistence.projections.CourseUserProfileProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, UUID> {
  Optional<CourseDetailsProjection> findDetailsById(UUID id);

  Page<CourseProjection> findAllByDeletedFalseAndApprovedTrue(Pageable pageable);

  @Query(
      """
                SELECT DISTINCT
                    c.id as id,
                    c.title as title,
                    c.capeImageUrl as capeImageUrl,
                    c.createdAt as createdAt,
                    c.updatedAt as updatedAt,
                    c.totalScore as totalScore,
                    c.totalReviews as totalReviews,
                    c.averageScore as averageScore,
                    p as professor
                FROM
                    CourseEntity c
                JOIN
                    c.tags t
                JOIN
                    c.professor p
                WHERE
                    c.approved = true
                AND
                    c.deleted = false
                AND
                    t.id IN :tagIds
            """)
  Page<CourseProjection> findByTagIds(Long[] tagIds, Pageable pageable);

  @Query(
      """
                SELECT
                    c
                FROM
                    CourseEntity c
                WHERE
                    c.approved = true
                AND
                    c.deleted = false
                AND
                    UPPER(CONCAT(c.title, c.description))
                LIKE
                    UPPER(CONCAT('%', :term, '%'))
            """)
  Page<CourseProjection> findByTerm(String term, Pageable pageable);

  @Query(
      """
                SELECT DISTINCT
                    c
                FROM
                    CourseEntity c
                JOIN
                    c.tags t
                WHERE
                    c.approved = true
                AND
                    c.deleted = false
                AND
                    t.id IN :tagIds
                AND
                    UPPER(CONCAT(c.title, c.description))
                LIKE
                    UPPER(CONCAT('%', :term, '%'))
            """)
  Page<CourseProjection> findByTagIdsAndTerm(Long[] tagIds, String term, Pageable pageable);

  List<CourseUserProfileProjection> findCourseUserProfileProjectionByProfessorId(UUID userId);

  @Modifying
  @Query(nativeQuery = true, value = "CALL update_courses_reviews()")
  void updateCourseReviews();
}
