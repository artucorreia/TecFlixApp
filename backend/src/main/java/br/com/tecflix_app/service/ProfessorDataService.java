package br.com.tecflix_app.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.tecflix_app.modules.professorData.infra.dtos.v1.CreateProfessorDataDTO;
import br.com.tecflix_app.modules.user.infra.dtos.v1.UserDTO;
import br.com.tecflix_app.mapper.contract.IMapperService;
import br.com.tecflix_app.modules.professorData.infra.persistence.ProfessorDataEntity;
import br.com.tecflix_app.modules.professorData.infra.persistence.ProfessorDataRepository;

@Service
public class ProfessorDataService {
  private final Logger LOGGER = Logger.getLogger(ProfessorDataService.class.getName());

  private final ProfessorDataRepository repository;
  private final IMapperService mapper;

  public ProfessorDataService(ProfessorDataRepository repository, IMapperService mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  public Optional<Long> findIdByUserId(UUID userId) {
    LOGGER.info("Finding professorData id by user id");
    return repository.findIdByUserId(userId);
  }

  @Transactional(rollbackFor = Exception.class)
  public void create(UserDTO user, LocalDateTime createdAt, CreateProfessorDataDTO data) {
    LOGGER.info("Saving professor data");

    data.setUser(user);
    data.setBiography(data.getBiography().trim());
    data.setContact(data.getContact().trim());
    data.setCpf(data.getCpf().trim());
    data.setProfileImage(data.getProfileImage().trim());
    data.setCreatedAt(createdAt);

    ProfessorDataEntity entity = mapper.map(data, ProfessorDataEntity.class);

    repository.save(entity);
  }
}
