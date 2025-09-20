package br.com.tecflix_app.modules.occupation.infra.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OccupationRepository extends JpaRepository<OccupationEntity, Long> {}
