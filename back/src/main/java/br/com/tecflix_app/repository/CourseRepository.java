package br.com.tecflix_app.repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.tecflix_app.model.Course;
import br.com.tecflix_app.projection.CourseDetailsProjection;

@Repository
public interface CourseRepository extends JpaRepository<Course, UUID> {
    Optional<CourseDetailsProjection> findDetailsById(UUID id);
    
    @Query(
        nativeQuery = true,
        value = """
                    SELECT 
                        c.*
                    FROM 
                        courses c
                    INNER JOIN 
                        courses_tags ct ON ct.course_id = c.id
                    INNER JOIN 
                        tags t ON ct.tag_id = t.id
                    WHERE 
                        t.id IN :tagIds
                """
    )
    Set<Course> findByTagIds(Long[] tagIds);

    @Query(
        nativeQuery = true,
        value = """
                    SELECT
                        *
                    FROM
                        courses c
                    WHERE
                            UPPER(CONCAT(c.title, c.description)) 
                        LIKE 
                            UPPER(CONCAT('%', :term, '%'))
                """
    )
    Set<Course> findByTerm(String term);


    @Query(
        nativeQuery = true,
        value = """
                    SELECT 
                        c.*
                    FROM 
                        courses c
                    INNER JOIN 
                        courses_tags ct ON ct.course_id = c.id
                    INNER JOIN 
                        tags t ON ct.tag_id = t.id
                    WHERE 
                        t.id IN (:tagIds)
                    AND 
                            UPPER(CONCAT(c.title, c.description)) 
                        LIKE 
                            UPPER(CONCAT('%', :term, '%'))
                """
    )
    Set<Course> findByTagIdsAndTerm(Long[] tagIds, String term);

    @Modifying
    @Query(
        nativeQuery = true,
        value = """
                    UPDATE 
                        courses 
                    SET 
                        total_score_reviews = (
                            SELECT COUNT(r.id) FROM reviews r WHERE r.course_id = courses.id
                        ), 
                        total_reviews = (
                            SELECT SUM(r.score) FROM reviews r WHERE r.course_id = courses.id
                        );
                """
    )
    void updateCourseReviews();
}
