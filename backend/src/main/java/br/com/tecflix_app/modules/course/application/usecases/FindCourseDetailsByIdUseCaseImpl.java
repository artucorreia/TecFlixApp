package br.com.tecflix_app.modules.course.application.usecases;

import br.com.tecflix_app.modules.course.application.domain.entity.Course;
import br.com.tecflix_app.modules.course.application.gateways.CourseRepositoryGateway;
import br.com.tecflix_app.modules.shared.exception.general.ResourceNotFoundException;

import java.util.UUID;

public class FindCourseDetailsByIdUseCaseImpl implements FindCourseDetailsByIdUseCase {

  private final CourseRepositoryGateway courseRepositoryGateway;

  public FindCourseDetailsByIdUseCaseImpl(CourseRepositoryGateway courseRepositoryGateway) {
    this.courseRepositoryGateway = courseRepositoryGateway;
  }

  @Override
  public Course execute(UUID id) {
    return courseRepositoryGateway
        .findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Nenhum curso encontrado para este id"));
  }
}
