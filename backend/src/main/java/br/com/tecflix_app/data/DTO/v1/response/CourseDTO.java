package br.com.tecflix_app.data.DTO.v1.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.hateoas.RepresentationModel;

public class CourseDTO extends RepresentationModel<CourseDTO> {
  private UUID id;
  private String title;
  private String description;
  private String capeImage;
  private Boolean active;
  private LocalDateTime createdAt;
  private Long totalScore;
  private Long totalReviews;
  private Double averageScore;
  private UserDTO professor;
  private List<ModuleDTO> modules;
  private List<TagDTO> tags;

  public CourseDTO() {}

  public CourseDTO(UUID id) {
    this.id = id;
  }

  public CourseDTO(
      UUID id,
      String title,
      String description,
      String capeImage,
      Boolean active,
      LocalDateTime createdAt,
      Long totalScore,
      Long totalReviews,
      Double averageScore,
      UserDTO professor,
      List<ModuleDTO> modules,
      List<TagDTO> tags) {
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

  public UserDTO getProfessor() {
    return professor;
  }

  public void setProfessor(UserDTO professor) {
    this.professor = professor;
  }

  public List<ModuleDTO> getModules() {
    return modules;
  }

  public void setModules(List<ModuleDTO> modules) {
    this.modules = modules;
  }

  public List<TagDTO> getTags() {
    return tags;
  }

  public void setTags(List<TagDTO> tags) {
    this.tags = tags;
  }
}
