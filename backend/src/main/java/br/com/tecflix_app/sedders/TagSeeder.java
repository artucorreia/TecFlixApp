package br.com.tecflix_app.sedders;

import br.com.tecflix_app.modules.tag.infra.persistence.TagEntity;
import br.com.tecflix_app.modules.tag.infra.persistence.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    TagEntity tag1 =
        TagEntity.builder()
            .name("Desenvolvimento Mobile")
            .createdAt(LocalDateTime.now())
            .deleted(false)
            .build();
    TagEntity tag2 =
        TagEntity.builder()
            .name("Desenvolvimento Web")
            .createdAt(LocalDateTime.now())
            .deleted(false)
            .build();
    TagEntity tag3 =
        TagEntity.builder()
            .name("Desenvolvimento")
            .createdAt(LocalDateTime.now())
            .deleted(false)
            .build();
    TagEntity tag4 =
        TagEntity.builder()
            .name("Desenvolvimento Back-end")
            .createdAt(LocalDateTime.now())
            .deleted(false)
            .build();
    TagEntity tag5 =
        TagEntity.builder()
            .name("Desenvolvimento Front-end")
            .createdAt(LocalDateTime.now())
            .deleted(false)
            .build();
    TagEntity tag6 =
        TagEntity.builder()
            .name("Desenvolvimento de Games")
            .createdAt(LocalDateTime.now())
            .deleted(false)
            .build();
    TagEntity tag7 =
        TagEntity.builder().name("Security").createdAt(LocalDateTime.now()).deleted(false).build();
    TagEntity tag8 =
        TagEntity.builder().name("Redes").createdAt(LocalDateTime.now()).deleted(false).build();
    TagEntity tag9 =
        TagEntity.builder()
            .name("Informática")
            .createdAt(LocalDateTime.now())
            .deleted(false)
            .build();
    TagEntity tag10 =
        TagEntity.builder()
            .name("Manutenção de Computadores")
            .createdAt(LocalDateTime.now())
            .deleted(false)
            .build();
    TagEntity tag11 =
        TagEntity.builder()
            .name("Servidores")
            .createdAt(LocalDateTime.now())
            .deleted(false)
            .build();
    TagEntity tag12 =
        TagEntity.builder().name("Linux").createdAt(LocalDateTime.now()).deleted(false).build();
    TagEntity tag13 =
        TagEntity.builder()
            .name("Sistemas Embarcados")
            .createdAt(LocalDateTime.now())
            .deleted(false)
            .build();
    TagEntity tag14 =
        TagEntity.builder().name("Arduino").createdAt(LocalDateTime.now()).deleted(false).build();
    TagEntity tag15 =
        TagEntity.builder().name("Hardware").createdAt(LocalDateTime.now()).deleted(false).build();
    TagEntity tag16 =
        TagEntity.builder()
            .name("Web Desing")
            .createdAt(LocalDateTime.now())
            .deleted(false)
            .build();
    TagEntity tag17 =
        TagEntity.builder().name("UX/UI").createdAt(LocalDateTime.now()).deleted(false).build();
    TagEntity tag18 =
        TagEntity.builder()
            .name("Engenharia de Software")
            .createdAt(LocalDateTime.now())
            .deleted(false)
            .build();
    TagEntity tag19 =
        TagEntity.builder()
            .name("Banco de dados")
            .createdAt(LocalDateTime.now())
            .deleted(false)
            .build();
    TagEntity tag20 =
        TagEntity.builder()
            .name("Inteligência Artificial")
            .createdAt(LocalDateTime.now())
            .deleted(false)
            .build();
    return List.of(
        tag1, tag2, tag3, tag4, tag5, tag6, tag7, tag8, tag9, tag10, tag11, tag12, tag13, tag14,
        tag15, tag16, tag17, tag18, tag19, tag20);
  }
}
