package br.com.tecflix_app.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.tecflix_app.model.Class;

@Repository
public interface ClassRepository extends JpaRepository<Class, UUID> {

}
