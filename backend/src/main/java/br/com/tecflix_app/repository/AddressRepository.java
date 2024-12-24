package br.com.tecflix_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.tecflix_app.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> { }
