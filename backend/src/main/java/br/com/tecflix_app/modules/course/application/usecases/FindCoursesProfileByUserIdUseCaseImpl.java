package br.com.tecflix_app.modules.course.application.usecases;

import br.com.tecflix_app.modules.course.application.domain.entity.Course;
import br.com.tecflix_app.modules.course.application.gateways.CourseRepositoryGateway;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

public class FindCoursesProfileByUserIdUseCaseImpl implements FindCoursesProfileByUserIdUseCase {
  private final Logger LOGGER =
      Logger.getLogger(FindCoursesProfileByUserIdUseCaseImpl.class.getName());
  private final CourseRepositoryGateway courseRepositoryGateway;

  public FindCoursesProfileByUserIdUseCaseImpl(CourseRepositoryGateway courseRepositoryGateway) {
    this.courseRepositoryGateway = courseRepositoryGateway;
  }

  @Override
  public List<Course> execute(UUID userId) {
    LOGGER.info("Finding courses for profile by user id: " + userId);
    return courseRepositoryGateway.findProfileByUserId(userId);
  }
}
