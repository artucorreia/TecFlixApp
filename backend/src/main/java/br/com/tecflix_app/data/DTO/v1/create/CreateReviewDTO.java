package br.com.tecflix_app.data.DTO.v1.create;

import br.com.tecflix_app.data.DTO.v1.response.CourseDTO;
import br.com.tecflix_app.data.DTO.v1.response.UserDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

public class CreateReviewDTO {

  @NotNull
  @Positive
  @Min(value = 1)
  @Max(value = 5)
  private Integer score;

  @Size(max = 255)
  private String comment;

  @JsonIgnore private UserDTO user;

  @JsonIgnore private CourseDTO course;

  @JsonIgnore private LocalDateTime createdAt = LocalDateTime.now();

  public CreateReviewDTO() {}

  public CreateReviewDTO(
      @NotNull @Positive @Min(1) @Max(5) Integer score,
      @Size(max = 255) String comment,
      UserDTO user,
      CourseDTO course,
      LocalDateTime createdAt) {
    this.score = score;
    this.comment = comment;
    this.user = user;
    this.course = course;
    this.createdAt = createdAt;
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

  public UserDTO getUser() {
    return user;
  }

  public void setUser(UserDTO user) {
    this.user = user;
  }

  public CourseDTO getCourse() {
    return course;
  }

  public void setCourse(CourseDTO course) {
    this.course = course;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }
}
