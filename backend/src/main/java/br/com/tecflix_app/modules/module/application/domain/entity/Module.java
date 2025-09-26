package br.com.tecflix_app.modules.module.application.domain.entity;

import br.com.tecflix_app.modules.course.application.domain.entity.Course;
import br.com.tecflix_app.modules.shared.application.domain.entity.BaseDomainEntity;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Module extends BaseDomainEntity {
  private Long id;
  private String title;
  private Course course;

  public Module() {}

  public Module(
      UUID createdBy,
      LocalDateTime createdAt,
      UUID updatedBy,
      LocalDateTime updatedAt,
      Boolean deleted,
      Long id,
      String title,
      Course course) {
    super(createdBy, createdAt, updatedBy, updatedAt, deleted);
    this.id = id;
    this.title = title;
    this.course = course;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Course getCourse() {
    return course;
  }

  public void setCourse(Course course) {
    this.course = course;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    Module module = (Module) o;
    return Objects.equals(id, module.id)
        && Objects.equals(title, module.title)
        && Objects.equals(course, module.course);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), id, title, course);
  }
}
