package br.com.tecflix_app.sedders;

import br.com.tecflix_app.modules.role.infra.persistence.RoleEntity;
import br.com.tecflix_app.modules.role.infra.persistence.RoleRepository;
import br.com.tecflix_app.modules.user.infra.persistence.UserEntity;
import br.com.tecflix_app.modules.user.infra.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class UserSeeder implements Seeder {
  private final Logger LOGGER = Logger.getLogger(UserSeeder.class.getName());
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;

  public void run() {
    if (userRepository.count() != 0) {
      LOGGER.info("Users already exist. Skipping seeder.");
      return;
    }
    LOGGER.info("Inserting users...");

    List<UserEntity> usersToInsert = getEntities();
    userRepository.saveAll(usersToInsert);

    LOGGER.info("Users inserted successfully.");
  }

  private List<UserEntity> getEntities() {
    Set<RoleEntity> allRoles = Set.copyOf(roleRepository.findAll());
    Set<RoleEntity> professorRoles =
        Set.copyOf(roleRepository.findByNameInIgnoreCase(List.of("PROFESSOR", "USER")));
    Set<RoleEntity> userRoles = Set.copyOf(roleRepository.findByNameInIgnoreCase(List.of("USER")));

    String defaultPassword = new BCryptPasswordEncoder().encode("12345678");
    UserEntity admin =
        UserEntity.builder()
            .name("Arthur Correia")
            .email("arthurcorria0109@gmail.com")
            .password(defaultPassword)
            .emailVerified(true)
            .emailVerifiedAt(LocalDateTime.now())
            .createdAt(LocalDateTime.now())
            .deleted(false)
            .roles(allRoles)
            .build();

    UserEntity professor =
        UserEntity.builder()
            .name("Professor Test")
            .email("professor@gmail.com")
            .password(defaultPassword)
            .emailVerified(true)
            .emailVerifiedAt(LocalDateTime.now())
            .createdAt(LocalDateTime.now())
            .deleted(false)
            .roles(professorRoles)
            .build();

    UserEntity user =
        UserEntity.builder()
            .name("User Test")
            .email("user@gmail.com")
            .password(defaultPassword)
            .emailVerified(true)
            .emailVerifiedAt(LocalDateTime.now())
            .createdAt(LocalDateTime.now())
            .deleted(false)
            .roles(userRoles)
            .build();

    return List.of(admin, professor, user);
  }
}
