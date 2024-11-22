package br.com.tecflix_app.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import br.com.tecflix_app.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    
    @Query("SELECT u FROM User u WHERE u.email = :email")
    UserDetails findUserDetailsByEmail(String email);
    
    // @Query("SELECT u.active FROM User u WHERE u.id = :id")
    // boolean findActiveById(UUID id);
    
    @Query("SELECT u.active FROM User u WHERE u.email = :email")
    boolean findActiveByEmail(String email);
    
    @Query("SELECT u.email FROM User u WHERE u.id = :id")
    String findEmailById(UUID id);
}