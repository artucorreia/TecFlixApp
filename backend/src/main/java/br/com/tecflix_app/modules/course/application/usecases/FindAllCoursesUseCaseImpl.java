package br.com.tecflix_app.modules.course.application.usecases;

import br.com.tecflix_app.modules.course.application.domain.entity.Course;
import br.com.tecflix_app.modules.course.application.gateways.CourseRepositoryGateway;
import br.com.tecflix_app.modules.shared.application.domain.entity.CustomPageResult;
import br.com.tecflix_app.modules.shared.exception.general.PaginationException;

import java.lang.reflect.Field;
import java.util.logging.Logger;

public class FindAllCoursesUseCaseImpl implements FindAllCoursesUseCase{
  private final Logger LOGGER = Logger.getLogger(FindAllCoursesUseCaseImpl.class.getName());
  private final CourseRepositoryGateway courseRepositoryGateway;

  public FindAllCoursesUseCaseImpl(CourseRepositoryGateway courseRepositoryGateway) {
    this.courseRepositoryGateway = courseRepositoryGateway;
  }

  @Override
  public CustomPageResult<Course> execute(int page, int size, String sortProperty, String direction) {
    LOGGER.info("Finding all courses");
    if (page < 0) throw new PaginationException("The page number must not be less than 0");
    if (size < 0) throw new PaginationException("The page size must not be less than 0");
    try {
      Field field = Course.class.getDeclaredField(sortProperty);
    } catch (Exception e) {
      throw new PaginationException(String.format("Course's property '%s' not found", sortProperty));
    }
    return courseRepositoryGateway.findAll(page, size, sortProperty, direction);
  }
}
