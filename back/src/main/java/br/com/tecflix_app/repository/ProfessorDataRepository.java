package br.com.tecflix_app.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.tecflix_app.model.ProfessorData;

@Repository
public interface ProfessorDataRepository extends JpaRepository<ProfessorData, Long> {
    @Query(
        nativeQuery = true,
        value = "SELECT pd.id from professors_data pd WHERE pd.user_id = :userId"
    )
    Optional<Long> findIdByUserId(UUID userId);
}
