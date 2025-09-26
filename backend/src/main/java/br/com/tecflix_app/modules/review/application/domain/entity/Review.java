package br.com.tecflix_app.modules.review.application.domain.entity;

import br.com.tecflix_app.modules.course.application.domain.entity.Course;
import br.com.tecflix_app.modules.shared.application.domain.entity.BaseDomainEntity;
import br.com.tecflix_app.modules.user.application.domain.entity.User;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Review extends BaseDomainEntity {
  private Long id;
  private User user;
  private Course course;
  private Integer score;
  private String comment;

  public Review() {}

  public Review(
      UUID createdBy,
      LocalDateTime createdAt,
      UUID updatedBy,
      LocalDateTime updatedAt,
      Boolean deleted,
      Long id,
      User user,
      Course course,
      Integer score,
      String comment) {
    super(createdBy, createdAt, updatedBy, updatedAt, deleted);
    this.id = id;
    this.user = user;
    this.course = course;
    this.score = score;
    this.comment = comment;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    Review review = (Review) o;
    return Objects.equals(id, review.id)
        && Objects.equals(user, review.user)
        && Objects.equals(course, review.course)
        && Objects.equals(score, review.score)
        && Objects.equals(comment, review.comment);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), id, user, course, score, comment);
  }
}
