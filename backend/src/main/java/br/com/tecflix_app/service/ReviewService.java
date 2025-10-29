package br.com.tecflix_app.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

import br.com.tecflix_app.modules.auth.application.gateways.AuthenticatedUserGateway;
import br.com.tecflix_app.modules.shared.exception.general.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.tecflix_app.modules.review.infra.presentation.dtos.v1.CreateReviewDTO;
import br.com.tecflix_app.modules.course.infra.presentation.dtos.v1.CourseResponseDTO;
import br.com.tecflix_app.modules.shared.dto.v1.GenericResponseDTO;
import br.com.tecflix_app.modules.review.infra.presentation.dtos.v1.ReviewResponseDTO;
import br.com.tecflix_app.modules.user.infra.presentation.dtos.v1.UserDTO;
import br.com.tecflix_app.modules.shared.exception.general.ActionNotAllowedException;
import br.com.tecflix_app.mapper.contract.IMapperService;
import br.com.tecflix_app.modules.review.infra.persistence.ReviewEntity;
import br.com.tecflix_app.modules.review.infra.persistence.ReviewRepository;

@Service
public class ReviewService {
  private final Logger LOGGER = Logger.getLogger(ReviewService.class.getName());

  private final ReviewRepository repository;
  private final CourseService courseService;
  private final AuthenticatedUserGateway authenticatedUserGateway;
  private final IMapperService mapper;

  public ReviewService(
      ReviewRepository repository,
      CourseService courseService,
      AuthenticatedUserGateway authenticatedUserGateway,
      IMapperService mapper) {
    this.repository = repository;
    this.courseService = courseService;
    this.authenticatedUserGateway = authenticatedUserGateway;
    this.mapper = mapper;
  }

  public List<ReviewResponseDTO> findByCourseId(UUID courseId) {
    LOGGER.info("Finding by course id");

    // check if courseId is valid
    courseService.findById(courseId);

    return mapper.map(repository.findByCourseIdAndDeletedFalse(courseId), ReviewResponseDTO.class);
  }

  @Transactional(rollbackFor = Exception.class)
  public GenericResponseDTO<Long> create(UUID courseId, CreateReviewDTO data) {
    LOGGER.info("Creating review");

    // check if courseId is valid
    courseService.findById(courseId);

    UUID userId =
        authenticatedUserGateway
            .findId()
            .orElseThrow(() -> new ResourceNotFoundException("Erro ao resgatar usuário logado"));
    Optional<ReviewEntity> entityId = repository.findByCourseIdAndUserIdAndDeletedFalse(courseId, userId);
    if (entityId.isPresent()) throw new ActionNotAllowedException("Você já avaliou este curso");

    UserDTO user = new UserDTO();
    user.setId(userId);
//    data.setUser(user);

    CourseResponseDTO course = new CourseResponseDTO();
    course.setId(courseId);
//    data.setCourse(course);

    ReviewEntity entity = mapper.map(data, ReviewEntity.class);

    Long id = repository.save(entity).getId();

    return new GenericResponseDTO<>(id, "Avaliação criada com sucesso", LocalDateTime.now());
  }
}
