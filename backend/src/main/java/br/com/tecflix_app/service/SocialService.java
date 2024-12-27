package br.com.tecflix_app.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.tecflix_app.data.DTO.v1.create.CreateSocialDTO;
import br.com.tecflix_app.data.DTO.v1.response.UserDTO;
import br.com.tecflix_app.mapper.contract.IMapperService;
import br.com.tecflix_app.model.Social;
import br.com.tecflix_app.repository.SocialRepository;

@Service
public class SocialService {
    private final Logger LOGGER = Logger.getLogger(SocialService.class.getName());

    private final SocialRepository repository;
    private final IMapperService mapper;

    public SocialService(
        SocialRepository repository,
        IMapperService mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional(rollbackFor = Exception.class)
    public void create(UserDTO user, CreateSocialDTO data) {
        LOGGER.info("Saving professor's social");
        data.setUser(user);
        data.setUrl(data.getUrl().trim());

        Social entity = mapper.map(data, Social.class);

        repository.save(entity);
    }
    
    @Transactional(rollbackFor = Exception.class)
    public void createAll(UserDTO user, List<CreateSocialDTO> data) {
        LOGGER.info("Saving professor's socials");
        
        for (CreateSocialDTO social : data) {
            social.setUser(user);
            social.setUrl(social.getUrl().trim());
        }
        
        List<Social> entities = mapper.map(data, Social.class);

        repository.saveAll(entities);
    }
}
