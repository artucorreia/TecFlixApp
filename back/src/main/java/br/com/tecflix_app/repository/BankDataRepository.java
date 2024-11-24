package br.com.tecflix_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.tecflix_app.model.BankData;

@Repository
public interface BankDataRepository extends JpaRepository<BankData, Long> { }
