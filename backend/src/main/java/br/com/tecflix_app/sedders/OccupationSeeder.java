package br.com.tecflix_app.sedders;

import br.com.tecflix_app.modules.occupation.infra.persistence.OccupationEntity;
import br.com.tecflix_app.modules.occupation.infra.persistence.OccupationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    OccupationEntity occupation1 = new OccupationEntity(null, "PROFESSOR");
    OccupationEntity occupation2 = new OccupationEntity(null, "ADMINISTRADOR");
    OccupationEntity occupation3 = new OccupationEntity(null, "AUXILIAR DE COZINHA");
    OccupationEntity occupation4 = new OccupationEntity(null, "DESENVOLVEDOR");
    OccupationEntity occupation5 = new OccupationEntity(null, "PINTOR");
    OccupationEntity occupation6 = new OccupationEntity(null, "OUTRA");

    return List.of(occupation1, occupation2, occupation3, occupation4, occupation5, occupation6);
  }
}
