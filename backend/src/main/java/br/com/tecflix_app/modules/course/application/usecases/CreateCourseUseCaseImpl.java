package br.com.tecflix_app.modules.course.application.usecases;

import br.com.tecflix_app.modules.auth.application.gateways.AuthenticatedUserGateway;
import br.com.tecflix_app.modules.course.application.domain.entity.Course;
import br.com.tecflix_app.modules.course.application.gateways.CourseRepositoryGateway;
import br.com.tecflix_app.modules.shared.exception.auth.AuthenticatedUserException;
import br.com.tecflix_app.modules.shared.exception.general.ResourceNotFoundException;
import br.com.tecflix_app.modules.tag.application.domain.entity.Tag;
import br.com.tecflix_app.modules.tag.application.usecases.FindAllTagsByIdUseCase;
import br.com.tecflix_app.modules.user.application.domain.entity.User;
import br.com.tecflix_app.modules.user.application.usecases.FindUserByIdUseCase;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

public class CreateCourseUseCaseImpl implements CreateCourseUseCase {
  private final Logger LOGGER = Logger.getLogger(CreateCourseUseCaseImpl.class.getName());
  private final CourseRepositoryGateway courseRepositoryGateway;
  private final AuthenticatedUserGateway authenticatedUserGateway;
  private final FindAllTagsByIdUseCase findAllTagsByIdUseCase;
  private final FindUserByIdUseCase findUserByIdUseCase;

  public CreateCourseUseCaseImpl(
      CourseRepositoryGateway courseRepositoryGateway,
      AuthenticatedUserGateway authenticatedUserGateway,
      FindAllTagsByIdUseCase findAllTagsByIdUseCase,
      FindUserByIdUseCase findUserByIdUseCase) {
    this.courseRepositoryGateway = courseRepositoryGateway;
    this.authenticatedUserGateway = authenticatedUserGateway;
    this.findAllTagsByIdUseCase = findAllTagsByIdUseCase;
    this.findUserByIdUseCase = findUserByIdUseCase;
  }

  @Override
  public void execute(Course course) {
    LOGGER.info("Creating a new course");

    UUID optionalProfessorUUID =
        authenticatedUserGateway
            .findId()
            .orElseThrow(() -> new AuthenticatedUserException("Erro ao resgatar usuário logado"));
    User professor = findUserByIdUseCase.execute(optionalProfessorUUID);
    course.setProfessor(professor);

    List<Long> tagIds = course.getTags().stream().map(Tag::getId).toList();
    List<Tag> tags = findAllTagsByIdUseCase.execute(tagIds);

    validateTags(tagIds, tags);

    course.setTags(tags);
    course.setTitle(course.getTitle().trim());
    course.setDescription(course.getDescription().trim());
    course.setActive(true);
    course.setCreatedAt(LocalDateTime.now());
    course.setTotalScore(0L);
    course.setTotalReviews(0L);
    course.setAverageScore(0D);
    courseRepositoryGateway.save(course);
  }

  private void validateTags(List<Long> tagIds, List<Tag> tagsFound) {
    LOGGER.info("Validating if the tags exists");
    int courseTagListSize = tagIds.size();
    int foundTagListSize = tagsFound.size();
    if (courseTagListSize != foundTagListSize)
      throw new ResourceNotFoundException("Tags não encontradas");
  }
}
