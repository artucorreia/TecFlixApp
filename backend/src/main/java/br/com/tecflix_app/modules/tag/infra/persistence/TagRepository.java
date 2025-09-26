package br.com.tecflix_app.modules.tag.infra.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<TagEntity, Long> {
  List<TagEntity> findByDeletedFalse();
}
