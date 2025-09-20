package br.com.tecflix_app.sedders;

import br.com.tecflix_app.modules.tag.infra.persistence.TagEntity;
import br.com.tecflix_app.modules.tag.infra.persistence.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class TagSeeder implements Seeder {
  private final Logger LOGGER = Logger.getLogger(TagSeeder.class.getName());
  private final TagRepository tagRepository;

  public void run() {
    if (tagRepository.count() != 0) {
      LOGGER.info("Tags already exist. Skipping seeder.");
      return;
    }
    LOGGER.info("Inserting tags...");

    List<TagEntity> tagsToInsert = getEntities();
    tagRepository.saveAll(tagsToInsert);

    LOGGER.info("Tags inserted successfully.");
  }

  private List<TagEntity> getEntities() {
    TagEntity tag1 = new TagEntity(null, "Desenvolvimento Mobile");
    TagEntity tag2 = new TagEntity(null, "Desenvolvimento Web");
    TagEntity tag3 = new TagEntity(null, "Desenvolvimento");
    TagEntity tag4 = new TagEntity(null, "Desenvolvimento Back-end");
    TagEntity tag5 = new TagEntity(null, "Desenvolvimento Front-end");
    TagEntity tag6 = new TagEntity(null, "Desenvolvimento de Games");
    TagEntity tag7 = new TagEntity(null, "Security");
    TagEntity tag8 = new TagEntity(null, "Redes");
    TagEntity tag9 = new TagEntity(null, "Informática");
    TagEntity tag10 = new TagEntity(null, "Manutenção de Computadores");
    TagEntity tag11 = new TagEntity(null, "Servidores");
    TagEntity tag12 = new TagEntity(null, "Linux");
    TagEntity tag13 = new TagEntity(null, "Sistemas Embarcados");
    TagEntity tag14 = new TagEntity(null, "Arduino");
    TagEntity tag15 = new TagEntity(null, "Hardware");
    TagEntity tag16 = new TagEntity(null, "Web Desing");
    TagEntity tag17 = new TagEntity(null, "UX/UI");
    TagEntity tag18 = new TagEntity(null, "Engenharia de Software");
    TagEntity tag19 = new TagEntity(null, "Banco de dados");
    TagEntity tag20 = new TagEntity(null, "Inteligência Artificial");
    return List.of(
        tag1, tag2, tag3, tag4, tag5, tag6, tag7, tag8, tag9, tag10, tag11, tag12, tag13, tag14,
        tag15, tag16, tag17, tag18, tag19, tag20);
  }
}
