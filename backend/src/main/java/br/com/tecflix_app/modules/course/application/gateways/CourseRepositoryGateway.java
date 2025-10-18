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

  List<Course> findProfileByUserId(UUID userId);

  void save(Course course);
}
