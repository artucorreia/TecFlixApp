package br.com.tecflix_app.data.DTO.v1.create;

import br.com.tecflix_app.data.DTO.v1.response.TagDTO;
import br.com.tecflix_app.data.DTO.v1.response.UserDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

public class CreateCourseDTO {
  @NotNull
  @NotBlank
  @Size(min = 5, max = 40)
  private String title;

  @NotNull
  @NotBlank
  @Size(min = 5, max = 2000)
  private String description;

  @Size(max = 255)
  private String capeImage;

  @NotEmpty private List<TagDTO> tags;

  @JsonIgnore private UserDTO professor;

  @JsonIgnore private Boolean active = true;

  @JsonIgnore private LocalDateTime createdAt = LocalDateTime.now();

  @JsonIgnore private Long totalScore = 0L;

  @JsonIgnore private Long totalReviews = 0L;

  @JsonIgnore private Double averageScore = 0D;

  public CreateCourseDTO() {}

  public CreateCourseDTO(
      @NotNull @NotBlank @Size(min = 5, max = 40) String title,
      @NotNull @NotBlank @Size(min = 5, max = 2000) String description,
      @Size(max = 255) String capeImage,
      @NotEmpty List<TagDTO> tags,
      UserDTO professor,
      Boolean active,
      LocalDateTime createdAt,
      Long totalScore,
      Long totalReviews,
      Double averageScore) {
    this.title = title;
    this.description = description;
    this.capeImage = capeImage;
    this.tags = tags;
    this.professor = professor;
    this.active = active;
    this.createdAt = createdAt;
    this.totalScore = totalScore;
    this.totalReviews = totalReviews;
    this.averageScore = averageScore;
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

  public List<TagDTO> getTags() {
    return tags;
  }

  public void setTags(List<TagDTO> tags) {
    this.tags = tags;
  }

  public UserDTO getProfessor() {
    return professor;
  }

  public void setProfessor(UserDTO professor) {
    this.professor = professor;
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
}
