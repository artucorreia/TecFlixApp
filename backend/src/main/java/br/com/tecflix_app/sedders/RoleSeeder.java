package br.com.tecflix_app.sedders;

import br.com.tecflix_app.modules.role.infra.persistence.RoleEntity;
import br.com.tecflix_app.modules.role.infra.persistence.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class RoleSeeder implements Seeder {
  private final Logger LOGGER = Logger.getLogger(RoleSeeder.class.getName());
  private final RoleRepository roleRepository;

  public void run() {
    if (roleRepository.count() != 0) {
      LOGGER.info("Roles already exist. Skipping seeder.");
      return;
    }
    LOGGER.info("Inserting roles...");

    List<RoleEntity> rolesToInsert = getEntities();
    roleRepository.saveAll(rolesToInsert);

    LOGGER.info("Roles inserted successfully.");
  }

  private List<RoleEntity> getEntities() {
    RoleEntity adminRole =
        RoleEntity.builder().name("ADMIN").createdAt(LocalDateTime.now()).deleted(false).build();
    RoleEntity professorRole =
        RoleEntity.builder()
            .name("PROFESSOR")
            .createdAt(LocalDateTime.now())
            .deleted(false)
            .build();
    RoleEntity userRole =
        RoleEntity.builder().name("USER").createdAt(LocalDateTime.now()).deleted(false).build();
    return List.of(adminRole, professorRole, userRole);
  }
}
