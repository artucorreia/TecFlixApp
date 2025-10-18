package br.com.tecflix_app.modules.course.application.usecases;

import br.com.tecflix_app.modules.course.application.domain.entity.Course;
import br.com.tecflix_app.modules.shared.application.domain.entity.CustomPageResult;

/**
 * Use case responsible for retrieving all approved and non-deleted {@link Course} entities,
 * applying pagination and sorting based on the provided parameters.
 *
 * @author arthurcorreia.dev@gmail.com
 */
public interface FindAllCoursesUseCase {

  /**
   * Retrieves a paginated and sorted list of {@link Course} entities.
   *
   * @param page the zero-based index of the page to be retrieved
   * @param size the number of elements to be included in each page
   * @param sortProperty the property used to sort the results (e.g., "name", "createdAt")
   * @param direction the sort direction, either "ASC" for ascending or "DESC" for descending order
   * @return a {@link CustomPageResult} containing the paginated and sorted list of courses
   * @throws br.com.tecflix_app.modules.shared.exception.general.PaginationException if the
   *     pagination or sorting parameters are invalid
   */
  CustomPageResult<Course> execute(int page, int size, String sortProperty, String direction);
}
