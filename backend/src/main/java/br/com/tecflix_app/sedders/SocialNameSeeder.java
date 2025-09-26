package br.com.tecflix_app.sedders;

import br.com.tecflix_app.modules.gender.infra.persistence.GenderRepository;
import br.com.tecflix_app.modules.socialName.infra.persistence.SocialNameEntity;
import br.com.tecflix_app.modules.socialName.infra.persistence.SocialNameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class SocialNameSeeder implements Seeder {
  private final Logger LOGGER = Logger.getLogger(SocialNameSeeder.class.getName());
  private final SocialNameRepository socialNameRepository;

  public void run() {
    if (socialNameRepository.count() != 0) {
      LOGGER.info("Social names already exist. Skipping seeder.");
      return;
    }
    LOGGER.info("Inserting social names...");

    List<SocialNameEntity> gendersToInsert = getEntities();
    socialNameRepository.saveAll(gendersToInsert);

    LOGGER.info("Social names inserted successfully.");
  }

  private List<SocialNameEntity> getEntities() {
    SocialNameEntity socialName1 =
        SocialNameEntity.builder().name("X").createdAt(LocalDateTime.now()).deleted(false).build();
    SocialNameEntity socialName2 =
        SocialNameEntity.builder()
            .name("Instagram")
            .createdAt(LocalDateTime.now())
            .deleted(false)
            .build();
    SocialNameEntity socialName3 =
        SocialNameEntity.builder()
            .name("Facebook")
            .createdAt(LocalDateTime.now())
            .deleted(false)
            .build();
    SocialNameEntity socialName4 =
        SocialNameEntity.builder()
            .name("Reddit")
            .createdAt(LocalDateTime.now())
            .deleted(false)
            .build();
    SocialNameEntity socialName5 =
        SocialNameEntity.builder()
            .name("BLUESKY")
            .createdAt(LocalDateTime.now())
            .deleted(false)
            .build();
    SocialNameEntity socialName6 =
        SocialNameEntity.builder()
            .name("Telegram")
            .createdAt(LocalDateTime.now())
            .deleted(false)
            .build();
    SocialNameEntity socialName7 =
        SocialNameEntity.builder()
            .name("YouTube")
            .createdAt(LocalDateTime.now())
            .deleted(false)
            .build();
    SocialNameEntity socialName8 =
        SocialNameEntity.builder()
            .name("Tik Tok")
            .createdAt(LocalDateTime.now())
            .deleted(false)
            .build();

    SocialNameEntity socialName9 =
        SocialNameEntity.builder()
            .name("LinkedIn")
            .createdAt(LocalDateTime.now())
            .deleted(false)
            .build();
    SocialNameEntity socialName10 =
        SocialNameEntity.builder()
            .name("Twitch")
            .createdAt(LocalDateTime.now())
            .deleted(false)
            .build();
    SocialNameEntity socialName11 =
        SocialNameEntity.builder()
            .name("Blog")
            .createdAt(LocalDateTime.now())
            .deleted(false)
            .build();
    SocialNameEntity socialName12 =
        SocialNameEntity.builder()
            .name("Portfólio")
            .createdAt(LocalDateTime.now())
            .deleted(false)
            .build();
    SocialNameEntity socialName13 =
        SocialNameEntity.builder()
            .name("Site pessoal")
            .createdAt(LocalDateTime.now())
            .deleted(false)
            .build();

    return List.of(
        socialName1,
        socialName2,
        socialName3,
        socialName4,
        socialName5,
        socialName6,
        socialName7,
        socialName8,
        socialName9,
        socialName10,
        socialName11,
        socialName12,
        socialName13);
  }
}
