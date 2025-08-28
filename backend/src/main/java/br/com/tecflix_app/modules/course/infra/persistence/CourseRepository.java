package br.com.tecflix_app.modules.course.infra.persistence;

import br.com.tecflix_app.modules.course.infra.persistence.projections.CourseDetailsProjection;
import br.com.tecflix_app.modules.course.infra.persistence.projections.CourseProjection;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, UUID> {
  Optional<CourseDetailsProjection> findDetailsById(UUID id);

  Page<CourseProjection> findAllBy(Pageable pageable);

  @Query(
      """
                SELECT DISTINCT
                    c.id as id,
                    c.title as title,
                    c.capeImage as capeImage,
                    c.createdAt as createdAt,
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
                    UPPER(CONCAT(c.title, c.description))
                LIKE
                    UPPER(CONCAT('%', :term, '%'))
            """)
  Page<CourseProjection> findByTerm(String term, Pageable pageable);

  @Query(
      """
                SELECT
                    c
                FROM
                    CourseEntity c
                JOIN
                    c.tags t
                WHERE
                    t.id IN :tagIds
                AND
                    UPPER(CONCAT(c.title, c.description))
                LIKE
                    UPPER(CONCAT('%', :term, '%'))
            """)
  Page<CourseProjection> findByTagIdsAndTerm(Long[] tagIds, String term, Pageable pageable);

  @Modifying
  @Query(nativeQuery = true, value = "CALL update_courses_reviews()")
  void updateCourseReviews();
}
