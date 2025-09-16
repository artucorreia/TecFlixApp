package br.com.tecflix_app.modules.emailCode.infra.persistence;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailCodeRepository extends JpaRepository<EmailCodeEntity, Long> {

  Optional<EmailCodeEntity> findByCodeAndUserId(String code, UUID userId);

  @Modifying
  void deleteAllByCreatedAtBefore(LocalDateTime time);

  @Modifying
  void deleteByUserId(UUID userId);
}
