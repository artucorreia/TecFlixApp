package br.com.tecflix_app.modules.course.application.domain.entity;

import br.com.tecflix_app.modules.tag.application.domain.entity.Tag;
import br.com.tecflix_app.modules.user.application.domain.entity.User;
import br.com.tecflix_app.modules.module.application.domain.entity.Module;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Course implements Serializable {
  private UUID id;
  private String title;
  private String description;
  private String capeImage;
  private Boolean active;
  private LocalDateTime createdAt;
  private Long totalScore;
  private Long totalReviews;
  private Double averageScore;
  private User professor;
  private List<User> students;
  private List<Module> modules;
  private List<Tag> tags;

  public Course() {}

  public Course(
      UUID id,
      String title,
      String description,
      String capeImage,
      Boolean active,
      LocalDateTime createdAt,
      Long totalScore,
      Long totalReviews,
      Double averageScore,
      User professor,
      List<User> students,
      List<Module> modules,
      List<Tag> tags) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.capeImage = capeImage;
    this.active = active;
    this.createdAt = createdAt;
    this.totalScore = totalScore;
    this.totalReviews = totalReviews;
    this.averageScore = averageScore;
    this.professor = professor;
    this.students = students;
    this.modules = modules;
    this.tags = tags;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getCapeImage() {
    return capeImage;
  }

  public void setCapeImage(String capeImage) {
    this.capeImage = capeImage;
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

  public Long getTotalScore() {
    return totalScore;
  }

  public void setTotalScore(Long totalScore) {
    this.totalScore = totalScore;
  }

  public Long getTotalReviews() {
    return totalReviews;
  }

  public void setTotalReviews(Long totalReviews) {
    this.totalReviews = totalReviews;
  }

  public Double getAverageScore() {
    return averageScore;
  }

  public void setAverageScore(Double averageScore) {
    this.averageScore = averageScore;
  }

  public User getProfessor() {
    return professor;
  }

  public void setProfessor(User professor) {
    this.professor = professor;
  }

  public List<User> getStudents() {
    return students;
  }

  public void setStudents(List<User> students) {
    this.students = students;
  }

  public List<Module> getModules() {
    return modules;
  }

  public void setModules(List<Module> modules) {
    this.modules = modules;
  }

  public List<Tag> getTags() {
    return tags;
  }

  public void setTags(List<Tag> tags) {
    this.tags = tags;
  }
}
