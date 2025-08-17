package br.com.tecflix_app.modules.review.application.domain.entity;

import br.com.tecflix_app.modules.course.application.domain.entity.Course;
import br.com.tecflix_app.modules.user.application.domain.entity.User;
import java.io.Serializable;
import java.time.LocalDateTime;

public class Review implements Serializable {
  private Long id;
  private Integer score;
  private String comment;
  private LocalDateTime createdAt;
  private User user;
  private Course course;

  public Review() {}

  public Review(
      Long id, Integer score, String comment, LocalDateTime createdAt, User user, Course course) {
    this.id = id;
    this.score = score;
    this.comment = comment;
    this.createdAt = createdAt;
    this.user = user;
    this.course = course;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getScore() {
    return score;
  }

  public void setScore(Integer score) {
    this.score = score;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Course getCourse() {
    return course;
  }

  public void setCourse(Course course) {
    this.course = course;
  }
}
