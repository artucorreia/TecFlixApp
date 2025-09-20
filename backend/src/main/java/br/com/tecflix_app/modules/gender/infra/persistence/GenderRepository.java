package br.com.tecflix_app.modules.gender.infra.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderRepository extends JpaRepository<GenderEntity, Long> {}
