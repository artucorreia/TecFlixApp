package br.com.tecflix_app.modules.module.infra.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ModuleRepository extends JpaRepository<ModuleEntity, Long> {
  List<ModuleEntity> findByCourseId(UUID courseId);
}
