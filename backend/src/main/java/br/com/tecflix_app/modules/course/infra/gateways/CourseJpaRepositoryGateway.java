package br.com.tecflix_app.modules.course.infra.gateways;

import br.com.tecflix_app.modules.course.application.domain.entity.Course;
import br.com.tecflix_app.modules.course.application.gateways.CourseRepositoryGateway;
import br.com.tecflix_app.modules.course.infra.gateways.mapper.CourseGatewaysMapper;
import br.com.tecflix_app.modules.course.infra.persistence.CourseEntity;
import br.com.tecflix_app.modules.course.infra.persistence.CourseRepository;
import br.com.tecflix_app.modules.course.infra.persistence.projections.CourseDetailsProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CourseJpaRepositoryGateway implements CourseRepositoryGateway {
  private final CourseRepository courseRepository;
  private final CourseGatewaysMapper courseGatewaysMapper;

  @Override
  public Optional<Course> findById(UUID id) {
    Optional<CourseEntity> courseEntityOptional = courseRepository.findById(id);
    return courseEntityOptional.map(courseGatewaysMapper::map);
  }

  @Override
  public Optional<Course> findByDetailsId(UUID id) {
    Optional<CourseDetailsProjection> courseDetailsProjectionOptional =
        courseRepository.findDetailsById(id);
    return courseDetailsProjectionOptional.map(courseGatewaysMapper::map);
  }

  @Override
  public List<Course> findProfileByUserId(UUID userId) {
    List<CourseEntity> courseEntities =
        courseRepository.findCourseUserProfileProjectionByProfessorId(userId).stream()
            .map(courseGatewaysMapper::map)
            .toList();
    return courseEntities.stream().map(courseGatewaysMapper::map).toList();
  }

  @Override
  public void save(Course course) {
    CourseEntity courseEntity = courseGatewaysMapper.map(course);
    courseRepository.save(courseEntity);
  }
}
