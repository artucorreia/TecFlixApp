package br.com.tecflix_app.data.DTO.v1.response;

import java.time.LocalDateTime;

public class ReviewDTO {
  private Long id;
  private Integer score;
  private String comment;
  private LocalDateTime createdAt;
  private UserDTO user;

  public ReviewDTO() {}

  public ReviewDTO(Long id, Integer score, String comment, LocalDateTime createdAt, UserDTO user) {
    this.id = id;
    this.score = score;
    this.comment = comment;
    this.createdAt = createdAt;
    this.user = user;
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

  public UserDTO getUser() {
    return user;
  }

  public void setUser(UserDTO user) {
    this.user = user;
  }
}
