package br.com.tecflix_app.sedders;

import br.com.tecflix_app.modules.role.infra.persistence.RoleEntity;
import br.com.tecflix_app.modules.role.infra.persistence.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    RoleEntity adminRole = new RoleEntity(null, "ADMIN");
    RoleEntity professorRole = new RoleEntity(null, "PROFESSOR");
    RoleEntity userRole = new RoleEntity(null, "USER");
    return List.of(adminRole, professorRole, userRole);
  }
}
