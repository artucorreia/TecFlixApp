package br.com.tecflix_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.tecflix_app.model.BankAccountData;

@Repository
public interface BankAccountDataRepository extends JpaRepository<BankAccountData, Long> { }
