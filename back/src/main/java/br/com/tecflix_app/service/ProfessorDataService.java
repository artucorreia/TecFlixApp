package br.com.tecflix_app.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import br.com.tecflix_app.data.DTO.v1.create.CreateProfessorDataDTO;
import br.com.tecflix_app.data.DTO.v1.response.UserDTO;
import br.com.tecflix_app.mapper.Mapper;
import br.com.tecflix_app.model.ProfessorData;
import br.com.tecflix_app.repository.ProfessorDataRepository;

@Service
public class ProfessorDataService {
    private final Logger LOGGER = Logger.getLogger(ProfessorDataService.class.getName());

    private final ProfessorDataRepository repository;
    private final Mapper mapper;

    public ProfessorDataService(
        ProfessorDataRepository repository,
        Mapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Optional<Long> findIdByUserId(UUID userId) {
        return repository.findIdByUserId(userId);
    }

    public void create(
        UserDTO user, 
        LocalDateTime createdAt,
        CreateProfessorDataDTO data
    ) {
        LOGGER.info("Saving professor data");
        
        data.setUser(user);
        data.setBiography(data.getBiography().trim());
        data.setContact(data.getContact().trim());
        data.setCpf(data.getCpf().trim());
        data.setProfileImage(data.getProfileImage().trim());
        data.setCreatedAt(createdAt);

        ProfessorData entity = mapper.map(data, ProfessorData.class);

        repository.save(entity);
    }
}