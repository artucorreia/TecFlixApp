package br.com.tecflix_app.modules.user.infra.persistence;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.tecflix_app.modules.user.infra.persistence.projections.UserAccountProjection;
import br.com.tecflix_app.modules.user.infra.persistence.projections.UserBasicProjection;
import br.com.tecflix_app.modules.user.infra.persistence.projections.UserProfileProjection;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

  Optional<UserAccountProjection> findDataById(UUID id);

  @Query("SELECT u FROM UserEntity u WHERE u.email = :email")
  Optional<UserEntity> findByEmail(String email);

  @Query("SELECT u.emailVerified FROM UserEntity u WHERE UPPER(u.email) = UPPER(:email)")
  Optional<Boolean> findEmailVerifiedByEmail(String email);

  @Query("SELECT u.email FROM UserEntity u WHERE u.id = :id")
  Optional<String> findEmailById(UUID id);

//  @Query("SELECT u.role FROM UserEntity u WHERE u.id = :id")
//  Optional<Role> findRoleById(UUID id);

  @Query("SELECT u.id FROM UserEntity u WHERE u.email = :email")
  Optional<UUID> findIdByEmail(String email);

  Optional<UserBasicProjection> findUserBasicProjectionByEmail(String email);

  Optional<UserProfileProjection> findProfileById(UUID id);
}
