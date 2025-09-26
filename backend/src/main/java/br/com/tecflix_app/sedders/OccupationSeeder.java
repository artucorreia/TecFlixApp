package br.com.tecflix_app.sedders;

import br.com.tecflix_app.modules.occupation.infra.persistence.OccupationEntity;
import br.com.tecflix_app.modules.occupation.infra.persistence.OccupationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class OccupationSeeder implements Seeder {
  private final Logger LOGGER = Logger.getLogger(OccupationSeeder.class.getName());
  private final OccupationRepository occupationRepository;

  public void run() {
    if (occupationRepository.count() != 0) {
      LOGGER.info("Occupations already exist. Skipping seeder.");
      return;
    }
    LOGGER.info("Inserting occupations...");

    List<OccupationEntity> occupationsToInsert = getOccupationEntities();
    occupationRepository.saveAll(occupationsToInsert);

    LOGGER.info("Occupations inserted successfully.");
  }

  private List<OccupationEntity> getOccupationEntities() {
    OccupationEntity occupation1 =
        OccupationEntity.builder()
            .name("PROFESSOR")
            .createdAt(LocalDateTime.now())
            .deleted(false)
            .build();
    OccupationEntity occupation2 =
        OccupationEntity.builder()
            .name("ADMINISTRADOR")
            .createdAt(LocalDateTime.now())
            .deleted(false)
            .build();
    OccupationEntity occupation3 =
        OccupationEntity.builder()
            .name("AUXILIAR DE COZINHA")
            .createdAt(LocalDateTime.now())
            .deleted(false)
            .build();
    OccupationEntity occupation4 =
        OccupationEntity.builder()
            .name("DESENVOLVEDOR")
            .createdAt(LocalDateTime.now())
            .deleted(false)
            .build();
    OccupationEntity occupation5 =
        OccupationEntity.builder()
            .name("PINTOR")
            .createdAt(LocalDateTime.now())
            .deleted(false)
            .build();
    OccupationEntity occupation6 =
        OccupationEntity.builder()
            .name("OUTRA")
            .createdAt(LocalDateTime.now())
            .deleted(false)
            .build();
    return List.of(occupation1, occupation2, occupation3, occupation4, occupation5, occupation6);
  }
}
