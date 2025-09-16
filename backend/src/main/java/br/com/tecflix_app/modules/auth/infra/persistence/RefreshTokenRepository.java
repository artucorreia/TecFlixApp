package br.com.tecflix_app.modules.auth.infra.persistence;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long> {
  Optional<RefreshTokenEntity> findByToken(String token);

  @Modifying
  @Query(nativeQuery = true, value = "DELETE FROM refresh_tokens WHERE user_id = :userId")
  void deleteByUserId(UUID userId);
}
