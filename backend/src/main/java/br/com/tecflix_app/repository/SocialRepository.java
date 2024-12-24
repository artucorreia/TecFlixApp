package br.com.tecflix_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.tecflix_app.model.Social;

@Repository
public interface SocialRepository extends JpaRepository<Social, Long> { }
