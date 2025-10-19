package br.com.tecflix_app.modules.course.application.usecases;

import br.com.tecflix_app.modules.course.application.domain.entity.Course;
import br.com.tecflix_app.modules.course.application.gateways.CourseRepositoryGateway;
import br.com.tecflix_app.modules.shared.application.domain.entity.CustomPageResult;
import br.com.tecflix_app.modules.shared.exception.general.PaginationException;

import java.lang.reflect.Field;
import java.util.logging.Logger;

public class FindCoursesBySearchUseCaseImpl implements FindCoursesBySearchUseCase {
  private final Logger LOGGER = Logger.getLogger(FindCoursesBySearchUseCaseImpl.class.getName());
  private final CourseRepositoryGateway courseRepositoryGateway;
  private final FindAllCoursesUseCase findAllCoursesUseCase;

  public FindCoursesBySearchUseCaseImpl(
      CourseRepositoryGateway courseRepositoryGateway,
      FindAllCoursesUseCase findAllCoursesUseCase) {
    this.courseRepositoryGateway = courseRepositoryGateway;
    this.findAllCoursesUseCase = findAllCoursesUseCase;
  }

  @Override
  public CustomPageResult<Course> execute(
      Long[] tags, String term, int page, int size, String sortProperty, String direction) {
    LOGGER.info("Find courses by search");
    if (page < 0) throw new PaginationException("The page number must not be less than 0");
    if (size < 0) throw new PaginationException("The page size must not be less than 0");
    try {
      Field field = Course.class.getDeclaredField(sortProperty);
    } catch (Exception e) {
      throw new PaginationException(
          String.format("Course's property '%s' not found", sortProperty));
    }

    if (tags == null && term == null)
      return findAllCoursesUseCase.execute(page, size, sortProperty, direction);
    if (term == null)
      return courseRepositoryGateway.searchByTags(tags, page, size, sortProperty, direction);
    if (tags == null)
      return courseRepositoryGateway.searchByTerm(term.trim(), page, size, sortProperty, direction);

    return courseRepositoryGateway.searchByTagsAndTerm(
        tags, term.trim(), page, size, sortProperty, direction);
  }
}
