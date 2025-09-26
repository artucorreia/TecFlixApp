package br.com.tecflix_app.sedders;

import br.com.tecflix_app.modules.gender.infra.persistence.GenderEntity;
import br.com.tecflix_app.modules.gender.infra.persistence.GenderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    GenderEntity gender1 =
        GenderEntity.builder()
            .name("HOMEM CISGÊNERO")
            .createdAt(LocalDateTime.now())
            .deleted(false)
            .build();
    GenderEntity gender2 =
        GenderEntity.builder()
            .name("MULHER CISGÊNERO")
            .createdAt(LocalDateTime.now())
            .deleted(false)
            .build();
    GenderEntity gender3 =
        GenderEntity.builder()
            .name("HOMEM TRANSGÊNERO")
            .createdAt(LocalDateTime.now())
            .deleted(false)
            .build();
    GenderEntity gender4 =
        GenderEntity.builder()
            .name("MULHER TRANSGÊNERO")
            .createdAt(LocalDateTime.now())
            .deleted(false)
            .build();
    GenderEntity gender5 =
        GenderEntity.builder()
            .name("PREFIRO NÃO DIZER")
            .createdAt(LocalDateTime.now())
            .deleted(false)
            .build();
    GenderEntity gender6 =
        GenderEntity.builder().name("OUTRO").createdAt(LocalDateTime.now()).deleted(false).build();

    return List.of(gender1, gender2, gender3, gender4, gender5, gender6);
  }
}
