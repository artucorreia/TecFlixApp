package br.com.tecflix_app.data.DTO.v1.response;

import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.hateoas.RepresentationModel;

public class ClassDTO extends RepresentationModel<ClassDTO> {
  private UUID id;
  private String title;
  private String videoPath;
  private Boolean active;
  private LocalDateTime createdAt;

  public ClassDTO() {}

  public ClassDTO(
      UUID id, String title, String videoPath, Boolean active, LocalDateTime createdAt) {
    this.id = id;
    this.title = title;
    this.videoPath = videoPath;
    this.active = active;
    this.createdAt = createdAt;
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

  public String getVideoPath() {
    return videoPath;
  }

  public void setVideoPath(String videoPath) {
    this.videoPath = videoPath;
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
