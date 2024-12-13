package br.com.tecflix_app.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.tecflix_app.data.DTO.v1.create.CreateReviewDTO;
import br.com.tecflix_app.data.DTO.v1.response.GenericResponseDTO;
import br.com.tecflix_app.data.DTO.v1.response.ReviewDTO;
import br.com.tecflix_app.exception.general.ActionNotAllowedException;
import br.com.tecflix_app.mapper.contract.IMapperService;
import br.com.tecflix_app.model.Review;
import br.com.tecflix_app.repository.ReviewRepository;
import br.com.tecflix_app.service.auth.jwt.TokenService;

@Service
public class ReviewService {
    private final Logger LOGGER = Logger.getLogger(ReviewService.class.getName());    

    private final ReviewRepository repository;
    private final CourseService courseService;
    private final TokenService tokenService;
    private final UserService userService;
    private final IMapperService mapper;

    @Autowired
    public ReviewService(
        ReviewRepository repository,
        CourseService courseService,
        TokenService tokenService,
        UserService userService,
        IMapperService mapper
    ) {
        this.repository = repository;
        this.courseService = courseService;
        this.tokenService = tokenService;
        this.userService = userService;
        this.mapper = mapper;
    }

    public List<ReviewDTO> findByCourseId(UUID courseId) {
        LOGGER.info("Finding by course id");
        return mapper.map(
            repository.findByCourseId(courseId),
            ReviewDTO.class
        );
    }

    @Transactional(rollbackFor = Exception.class)
    public GenericResponseDTO<Long> create(CreateReviewDTO data) {
        LOGGER.info("Creating review");
        Optional<Long> entityId = repository.findIdByCourseIdAndUserId(data.getCourse().getId(), tokenService.getUserId());
        if (entityId.isPresent()) throw new ActionNotAllowedException("Voce ja adicionou uma avalicao a esse curso");
        
        data.setUser(userService.findById(tokenService.getUserId()));
        data.setCourse(courseService.findById(data.getCourse().getId()));

        Review entity = mapper.map(data, Review.class);
        Long id = repository.save(entity).getId();

        return new GenericResponseDTO<>(
            id,
            "Avaliação criada com sucesso",
            LocalDateTime.now()
        );
    }
}