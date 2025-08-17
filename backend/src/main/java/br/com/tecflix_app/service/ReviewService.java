package br.com.tecflix_app.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.tecflix_app.modules.review.infra.dtos.v1.CreateReviewDTO;
import br.com.tecflix_app.modules.course.infra.dtos.v1.CourseDTO;
import br.com.tecflix_app.shared.dto.v1.GenericResponseDTO;
import br.com.tecflix_app.modules.review.infra.dtos.v1.ReviewDTO;
import br.com.tecflix_app.modules.user.infra.dtos.v1.UserDTO;
import br.com.tecflix_app.shared.exception.general.ActionNotAllowedException;
import br.com.tecflix_app.mapper.contract.IMapperService;
import br.com.tecflix_app.modules.review.infra.persistence.ReviewEntity;
import br.com.tecflix_app.modules.review.infra.persistence.ReviewRepository;
import br.com.tecflix_app.modules.auth.application.usecases.jwt.TokenService;

@Service
public class ReviewService {
  private final Logger LOGGER = Logger.getLogger(ReviewService.class.getName());

  private final ReviewRepository repository;
  private final CourseService courseService;
  private final TokenService tokenService;
  private final IMapperService mapper;

  @Autowired
  public ReviewService(
      ReviewRepository repository,
      CourseService courseService,
      TokenService tokenService,
      IMapperService mapper) {
    this.repository = repository;
    this.courseService = courseService;
    this.tokenService = tokenService;
    this.mapper = mapper;
  }

  public List<ReviewDTO> findByCourseId(UUID courseId) {
    LOGGER.info("Finding by course id");

    // check if courseId is valid
    courseService.findById(courseId);

    return mapper.map(repository.findByCourseEntityId(courseId), ReviewDTO.class);
  }

  @Transactional(rollbackFor = Exception.class)
  public GenericResponseDTO<Long> create(UUID courseId, CreateReviewDTO data) {
    LOGGER.info("Creating review");

    // check if courseId is valid
    courseService.findById(courseId);

    UUID userId = tokenService.getUserId();
    Optional<Long> entityId = repository.findIdByCourseIdAndUserId(courseId, userId);
    if (entityId.isPresent()) throw new ActionNotAllowedException("Você já avaliou este curso");

    UserDTO user = new UserDTO();
    user.setId(userId);
    data.setUser(user);

    CourseDTO course = new CourseDTO();
    course.setId(courseId);
    data.setCourse(course);

    ReviewEntity entity = mapper.map(data, ReviewEntity.class);

    Long id = repository.save(entity).getId();

    return new GenericResponseDTO<>(id, "Avaliação criada com sucesso", LocalDateTime.now());
  }
}
