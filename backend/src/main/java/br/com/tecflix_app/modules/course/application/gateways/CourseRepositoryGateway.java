package br.com.tecflix_app.modules.course.application.gateways;

import br.com.tecflix_app.modules.course.application.domain.entity.Course;
import br.com.tecflix_app.modules.shared.application.domain.entity.CustomPageResult;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CourseRepositoryGateway {
  Optional<Course> findById(UUID id);

  Optional<Course> findByDetailsId(UUID id);

  CustomPageResult<Course> findAll(int page, int size, String sortProperty, String direction);

  CustomPageResult<Course> searchByTags(
      Long[] tags, int page, int size, String sortProperty, String direction);

  CustomPageResult<Course> searchByTerm(
      String term, int page, int size, String sortProperty, String direction);

  CustomPageResult<Course> searchByTagsAndTerm(
      Long[] tags, String term, int page, int size, String sortProperty, String direction);

  List<Course> findProfileByUserId(UUID userId);

  void save(Course course);
}
