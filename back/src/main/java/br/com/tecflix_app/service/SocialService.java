package br.com.tecflix_app.service;

import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import br.com.tecflix_app.data.DTO.v1.create.CreateSocialDTO;
import br.com.tecflix_app.data.DTO.v1.response.UserDTO;
import br.com.tecflix_app.mapper.Mapper;
import br.com.tecflix_app.model.Social;
import br.com.tecflix_app.repository.SocialRepository;

@Service
public class SocialService {
    private final Logger LOGGER = Logger.getLogger(SocialService.class.getName());

    private final SocialRepository repository;
    private final Mapper mapper;

    public SocialService(
        SocialRepository repository,
        Mapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public void create(
        UserDTO user, 
        CreateSocialDTO data
    ) {
        LOGGER.info("Saving professor data");
        data.setUser(user);
        data.setUrl(data.getUrl().trim());

        Social entity = mapper.map(data, Social.class);

        repository.save(entity);
    }
}
