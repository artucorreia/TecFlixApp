package br.com.tecflix_app.data.DTO.v1.create;

import br.com.tecflix_app.data.DTO.v1.response.CourseDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

public class CreateModuleDTO {
  @NotNull
  @NotBlank
  @Size(min = 5, max = 40)
  private String title;

  @NotNull private CourseDTO course;

  @JsonIgnore private Boolean active = true;

  @JsonIgnore private LocalDateTime createdAt = LocalDateTime.now();

  public CreateModuleDTO() {}

  public CreateModuleDTO(
      @NotNull @NotBlank @Size(min = 5, max = 40) String title,
      @NotNull CourseDTO course,
      Boolean active,
      LocalDateTime createdAt) {
    this.title = title;
    this.course = course;
    this.active = active;
    this.createdAt = createdAt;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public CourseDTO getCourse() {
    return course;
  }

  public void setCourse(CourseDTO course) {
    this.course = course;
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
}
