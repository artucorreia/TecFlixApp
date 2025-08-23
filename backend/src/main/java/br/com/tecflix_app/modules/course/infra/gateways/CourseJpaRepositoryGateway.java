package br.com.tecflix_app.modules.course.infra.gateways;

import br.com.tecflix_app.modules.course.application.domain.entity.Course;
import br.com.tecflix_app.modules.course.application.gateways.CourseRepositoryGateway;
import br.com.tecflix_app.modules.course.infra.gateways.mapper.CourseGatewaysMapper;
import br.com.tecflix_app.modules.course.infra.persistence.CourseEntity;
import br.com.tecflix_app.modules.course.infra.persistence.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CourseJpaRepositoryGateway implements CourseRepositoryGateway {

  private final CourseRepository courseRepository;
  private final CourseGatewaysMapper courseGatewaysMapper;

  @Override
  public Optional<Course> findById(UUID id) {
    Optional<CourseEntity> courseEntity = courseRepository.findById(id);
    return courseEntity.map(courseGatewaysMapper::entityToDomain);
  }

  @Override
  public void save(Course course) {
    CourseEntity courseEntity = courseGatewaysMapper.entityToDomain(course);
    courseRepository.save(courseEntity);
  }
}
