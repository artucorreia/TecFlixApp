package br.com.tecflix_app.modules.course.infra.gateways;

import br.com.tecflix_app.modules.course.application.domain.entity.Course;
import br.com.tecflix_app.modules.course.application.gateways.CourseRepositoryGateway;
import br.com.tecflix_app.modules.course.infra.gateways.mapper.CourseGatewaysMapper;
import br.com.tecflix_app.modules.course.infra.persistence.CourseEntity;
import br.com.tecflix_app.modules.course.infra.persistence.CourseRepository;
import br.com.tecflix_app.modules.course.infra.persistence.projections.CourseDetailsProjection;
import br.com.tecflix_app.modules.course.infra.persistence.projections.CourseProjection;
import br.com.tecflix_app.modules.shared.application.domain.entity.CustomPageResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
  public CustomPageResult<Course> findAll(
      int page, int size, String sortProperty, String direction) {
    Pageable pageable = generatePageable(page, size, sortProperty, direction);
    Page<CourseEntity> courseEntityPage =
        courseRepository
            .findAllByDeletedFalseAndApprovedTrue(pageable)
            .map(courseGatewaysMapper::map);
    List<Course> content =
        courseEntityPage.getContent().stream().map(courseGatewaysMapper::map).toList();
    return new CustomPageResult<>(
        content,
        courseEntityPage.getNumber(),
        courseEntityPage.getSize(),
        courseEntityPage.getTotalElements(),
        courseEntityPage.getTotalPages(),
        courseEntityPage.hasNext(),
        courseEntityPage.hasPrevious());
  }

  @Override
  public CustomPageResult<Course> searchByTags(
      Long[] tags, int page, int size, String sortProperty, String direction) {
    Pageable pageable = generatePageable(page, size, sortProperty, direction);
    Page<CourseEntity> courseEntityPage =
        courseRepository.findByTagIds(tags, pageable).map(courseGatewaysMapper::map);
    List<Course> content = courseEntityPage.stream().map(courseGatewaysMapper::map).toList();
    return new CustomPageResult<>(
        content,
        courseEntityPage.getNumber(),
        courseEntityPage.getSize(),
        courseEntityPage.getTotalElements(),
        courseEntityPage.getTotalPages(),
        courseEntityPage.hasNext(),
        courseEntityPage.hasPrevious());
  }

  @Override
  public CustomPageResult<Course> searchByTerm(
      String term, int page, int size, String sortProperty, String direction) {
    Pageable pageable = generatePageable(page, size, sortProperty, direction);
    Page<CourseEntity> courseEntityPage =
        courseRepository.findByTerm(term, pageable).map(courseGatewaysMapper::map);
    List<Course> content = courseEntityPage.stream().map(courseGatewaysMapper::map).toList();
    return new CustomPageResult<>(
        content,
        courseEntityPage.getNumber(),
        courseEntityPage.getSize(),
        courseEntityPage.getTotalElements(),
        courseEntityPage.getTotalPages(),
        courseEntityPage.hasNext(),
        courseEntityPage.hasPrevious());
  }

  @Override
  public CustomPageResult<Course> searchByTagsAndTerm(
      Long[] tags, String term, int page, int size, String sortProperty, String direction) {
    Pageable pageable = generatePageable(page, size, sortProperty, direction);
    Page<CourseEntity> courseEntityPage =
        courseRepository.findByTagIdsAndTerm(tags, term, pageable).map(courseGatewaysMapper::map);
    List<Course> content = courseEntityPage.stream().map(courseGatewaysMapper::map).toList();
    return new CustomPageResult<>(
        content,
        courseEntityPage.getNumber(),
        courseEntityPage.getSize(),
        courseEntityPage.getTotalElements(),
        courseEntityPage.getTotalPages(),
        courseEntityPage.hasNext(),
        courseEntityPage.hasPrevious());
  }

  @Override
  public void save(Course course) {
    CourseEntity courseEntity = courseGatewaysMapper.map(course);
    courseRepository.save(courseEntity);
  }

  private Pageable generatePageable(int page, int size, String sortProperty, String direction) {
    Sort.Direction sortDirection =
        "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
    return PageRequest.of(page, size, Sort.by(sortDirection, sortProperty));
  }
}
