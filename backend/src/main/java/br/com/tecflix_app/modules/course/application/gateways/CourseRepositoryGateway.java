package br.com.tecflix_app.modules.course.application.gateways;

import br.com.tecflix_app.modules.course.application.domain.entity.Course;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CourseRepositoryGateway {
  Optional<Course> findById(UUID id);

  Optional<Course> findByDetailsId(UUID id);

  List<Course> findProfileByUserId(UUID userId);

  void save(Course course);
}
