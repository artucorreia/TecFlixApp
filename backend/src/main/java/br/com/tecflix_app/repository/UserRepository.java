package br.com.tecflix_app.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import br.com.tecflix_app.model.User;
import br.com.tecflix_app.model.enums.Role;
import br.com.tecflix_app.projection.UserAccountProjection;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<UserAccountProjection> findDataById(UUID id);

    @Query("SELECT u FROM User u WHERE u.email = :email")
    UserDetails findUserDetailsByEmail(String email);

    @Query("SELECT u.active FROM User u WHERE u.email = :email")
    Optional<Boolean> findActiveByEmail(String email);

    @Query("SELECT u.email FROM User u WHERE u.id = :id")
    Optional<String> findEmailById(UUID id);

    @Query("SELECT u.role FROM User u WHERE u.id = :id")
    Optional<Role> findRoleById(UUID id);

    @Query("SELECT u.id FROM User u WHERE u.email = :email")
    Optional<UUID> findIdByEmail(String email);
}