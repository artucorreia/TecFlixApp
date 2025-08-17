package br.com.tecflix_app.modules.tag.application.domain.entity;

import br.com.tecflix_app.modules.course.application.domain.entity.Course;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class Tag implements Serializable {
  private Long id;
  private String name;
  private Boolean active;
  private LocalDateTime createdAt;
  private List<Course> courses;

  public Tag() {}

  public Tag(Long id, String name, Boolean active, LocalDateTime createdAt, List<Course> courses) {
    this.id = id;
    this.name = name;
    this.active = active;
    this.createdAt = createdAt;
    this.courses = courses;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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

  public List<Course> getCourses() {
    return courses;
  }

  public void setCourses(List<Course> courses) {
    this.courses = courses;
  }
}
