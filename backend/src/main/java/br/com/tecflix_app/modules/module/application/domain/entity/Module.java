package br.com.tecflix_app.modules.module.application.domain.entity;

import br.com.tecflix_app.modules.course.application.domain.entity.Course;
import br.com.tecflix_app.modules.courseClass.application.domain.entity.Class;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class Module implements Serializable {
  private Long id;
  private String title;
  private Boolean active;
  private LocalDateTime createdAt;
  private Course course;
  private List<Class> classEntities;

  public Module() {}

  public Module(
      Long id,
      String title,
      Boolean active,
      LocalDateTime createdAt,
      Course course,
      List<Class> classEntities) {
    this.id = id;
    this.title = title;
    this.active = active;
    this.createdAt = createdAt;
    this.course = course;
    this.classEntities = classEntities;
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

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public Course getCourse() {
    return course;
  }

  public void setCourse(Course course) {
    this.course = course;
  }

  public List<Class> getClassEntities() {
    return classEntities;
  }

  public void setClassEntities(List<Class> classEntities) {
    this.classEntities = classEntities;
  }
}
