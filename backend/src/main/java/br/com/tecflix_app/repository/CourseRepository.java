package br.com.tecflix_app.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.tecflix_app.model.Course;
import br.com.tecflix_app.projection.CourseDetailsProjection;
import br.com.tecflix_app.projection.CourseProjection;

@Repository
public interface CourseRepository extends JpaRepository<Course, UUID> {
    Optional<CourseDetailsProjection> findDetailsById(UUID id);
    
    List<CourseProjection> findAllBy();

    @Query(
        """
            SELECT 
                c.id as id,
                c.title as title,
                c.capeImage as capeImage,
                c.createdAt as createdAt,
                c.totalScoreReviews as totalScoreReviews,
                c.totalReviews as totalReviews,
                p as professor
            FROM 
                Course c
            JOIN 
                c.tags t
            JOIN
                c.professor p
            WHERE 
                t.id IN :tagIds
        """
    )
    Set<CourseProjection> findByTagIds(Long[] tagIds);

    @Query(
        """
            SELECT
                c
            FROM
                Course c
            WHERE
                UPPER(CONCAT(c.title, c.description)) 
            LIKE 
                UPPER(CONCAT('%', :term, '%'))
        """
    )
    Set<CourseProjection> findByTerm(String term);


    @Query(
        """
            SELECT 
                c
            FROM 
                Course c
            JOIN 
                c.tags t
            WHERE 
                t.id IN :tagIds
            AND 
                UPPER(CONCAT(c.title, c.description)) 
            LIKE 
                UPPER(CONCAT('%', :term, '%'))
        """
    )
    Set<CourseProjection> findByTagIdsAndTerm(Long[] tagIds, String term);

    @Modifying
    @Query(
        nativeQuery = true,
        value = """
                    UPDATE 
                        courses 
                    SET 
                        total_score_reviews = (
                            SELECT 
                                COUNT(r.id) 
                            FROM 
                                reviews r 
                            WHERE 
                                r.course_id = courses.id
                        ), 
                        total_reviews = (
                            SELECT 
                                SUM(r.score) 
                            FROM 
                                reviews r 
                            WHERE 
                                r.course_id = courses.id
                        );
                """
    )
    void updateCourseReviews();
}
