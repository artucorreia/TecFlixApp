package br.com.tecflix_app.modules.course.application.usecases;

import br.com.tecflix_app.modules.course.application.domain.entity.Course;

import java.util.List;
import java.util.UUID;

public interface FindCoursesProfileByUserIdUseCase {
  List<Course> execute(UUID userId);
}
