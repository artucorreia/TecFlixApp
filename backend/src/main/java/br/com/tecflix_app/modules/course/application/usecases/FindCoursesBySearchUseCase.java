package br.com.tecflix_app.modules.course.application.usecases;

import br.com.tecflix_app.modules.course.application.domain.entity.Course;
import br.com.tecflix_app.modules.shared.application.domain.entity.CustomPageResult;

/**
 * Use case responsible for retrieving {@link Course} entities based on a search criteria, applying
 * optional tag filters, pagination, and sorting.
 *
 * @author arthurcorreia.dev@gmail.com
 */
public interface FindCoursesBySearchUseCase {

  /**
   * Retrieves a paginated and sorted list of {@link Course} entities based on the provided filters
   * and search term.
   *
   * @param tags an array of tag IDs used to filter the courses (optional)
   * @param term the search term used to match course attributes such as name or description
   *     (optional)
   * @param page the zero-based index of the page to be retrieved
   * @param size the number of elements to include in each page
   * @param sortProperty the property used to sort the results (e.g., "name", "createdAt")
   * @param direction the sort direction, either "ASC" for ascending or "DESC" for descending order
   * @return a {@link CustomPageResult} containing the filtered, paginated, and sorted list of
   *     courses
   * @throws br.com.tecflix_app.modules.shared.exception.general.PaginationException if the pagination or sorting parameters are invalid
   */
  CustomPageResult<Course> execute(
      Long[] tags, String term, int page, int size, String sortProperty, String direction);
}
