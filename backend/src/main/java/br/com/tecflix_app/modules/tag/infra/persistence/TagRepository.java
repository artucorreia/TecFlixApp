package br.com.tecflix_app.modules.tag.infra.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<TagEntity, Long> {
  Optional<TagEntity> findByIdAndDeletedFalse(Long id);

  List<TagEntity> findByDeletedFalse();
}
