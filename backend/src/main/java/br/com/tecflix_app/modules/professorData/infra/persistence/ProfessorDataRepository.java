package br.com.tecflix_app.modules.professorData.infra.persistence;

import java.util.Optional;
import java.util.UUID;

import br.com.tecflix_app.modules.professorData.infra.persistence.projection.ProfessorDataUserProfileProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorDataRepository extends JpaRepository<ProfessorDataEntity, Long> {
  @Query(
      nativeQuery = true,
      value = "SELECT pd.id from professors_data pd WHERE pd.user_id = :userId")
  Optional<Long> findIdByUserId(UUID userId);

  Optional<ProfessorDataEntity> findByUserId(UUID userId);

  Optional<ProfessorDataEntity> findByPhoneNumber(String phoneNumber);

  Optional<ProfessorDataUserProfileProjection> findProfessorDataUserProfileProjectionByUserId(
      UUID userId);
}
