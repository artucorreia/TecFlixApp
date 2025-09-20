CREATE OR REPLACE PROCEDURE update_courses_reviews(p_course_id INT DEFAULT NULL) AS $$ 
    BEGIN IF p_course_id IS NOT NULL THEN -- Atualiza apenas o curso especificado
        WITH review_stats AS (
            SELECT 
                r.course_id,
                COUNT(r.id) AS total_reviews,
                SUM(r.score) AS total_score,
                ROUND(AVG(r.score), 1) AS average_score
            FROM 
                reviews r
            WHERE 
                r.course_id = p_course_id
            GROUP BY 
                r.course_id
        )
        UPDATE 
            courses
        SET 
            total_reviews = rs.total_reviews,
            total_score = rs.total_score,
            average_score = rs.average_score
        FROM 
            review_stats rs
        WHERE 
            courses.id = rs.course_id;
    ELSE -- Atualiza todos os cursos
        WITH review_stats AS (
            SELECT 
                r.course_id,
                COUNT(r.id) AS total_reviews,
                SUM(r.score) AS total_score,
                ROUND(AVG(r.score), 2) AS average_score
            FROM 
                reviews r
            GROUP BY 
                r.course_id
        )
        UPDATE 
            courses
        SET 
            total_reviews = rs.total_reviews,
            total_score = rs.total_score,
            average_score = rs.average_score
        FROM 
            review_stats rs
        WHERE 
            courses.id = rs.course_id;
    END IF;
END;
$$ LANGUAGE plpgsql;