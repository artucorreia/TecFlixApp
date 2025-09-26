package br.com.tecflix_app.service;

import br.com.tecflix_app.mapper.contract.IMapperService;
import br.com.tecflix_app.modules.social.infra.persistence.SocialRepository;

import java.util.logging.Logger;
import org.springframework.stereotype.Service;

@Service
public class SocialService {
  private final Logger LOGGER = Logger.getLogger(SocialService.class.getName());

  private final SocialRepository repository;
  private final IMapperService mapper;

  public SocialService(SocialRepository repository, IMapperService mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

//  @Transactional(rollbackFor = Exception.class)
//  public void create(UserDTO user, CreateSocialDTO data) {
//    LOGGER.info("Saving professor's social");
//    data.setUser(user);
//    data.setUrl(data.getUrl().trim());
//
//    SocialEntity entity = mapper.map(data, SocialEntity.class);
//
//    repository.save(entity);
//  }

//  @Transactional(rollbackFor = Exception.class)
//  public void createAll(UserDTO user, List<CreateSocialDTO> data) {
//    LOGGER.info("Saving professor's socials");
//
//    for (CreateSocialDTO social : data) {
//      social.setUser(user);
//      social.setUrl(social.getUrl().trim());
//    }
//
//    List<SocialEntity> entities = mapper.map(data, SocialEntity.class);
//
//    repository.saveAll(entities);
//  }
}
