package br.com.tecflix_app.sedders;

import br.com.tecflix_app.modules.gender.infra.persistence.GenderEntity;
import br.com.tecflix_app.modules.gender.infra.persistence.GenderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class GenderSeeder implements Seeder {
  private final Logger LOGGER = Logger.getLogger(GenderSeeder.class.getName());
  private final GenderRepository genderRepository;

  public void run() {
    if (genderRepository.count() != 0) {
      LOGGER.info("Genders already exist. Skipping seeder.");
      return;
    }
    LOGGER.info("Inserting genders...");

    List<GenderEntity> gendersToInsert = getEntities();
    genderRepository.saveAll(gendersToInsert);

    LOGGER.info("Genders inserted successfully.");
  }

  private List<GenderEntity> getEntities() {
    GenderEntity gender1 = new GenderEntity(null, "HOMEM CISGÊNERO");
    GenderEntity gender2 = new GenderEntity(null, "MULHER CISGÊNERO");
    GenderEntity gender3 = new GenderEntity(null, "HOMEM TRANSGÊNERO");
    GenderEntity gender4 = new GenderEntity(null, "MULHER TRANSGÊNERO");
    GenderEntity gender5 = new GenderEntity(null, "PREFIRO NÃO DIZER");
    GenderEntity gender6 = new GenderEntity(null, "OUTRO");

    return List.of(gender1, gender2, gender3, gender4, gender5, gender6);
  }
}
