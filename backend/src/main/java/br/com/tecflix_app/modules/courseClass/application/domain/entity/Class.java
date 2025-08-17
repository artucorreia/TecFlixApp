package br.com.tecflix_app.modules.courseClass.application.domain.entity;

import br.com.tecflix_app.modules.module.application.domain.entity.Module;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public class Class implements Serializable {
  private UUID id;
  private String title;
  private String videoPath;
  private Boolean active;
  private LocalDateTime createdAt;
  private Module module;

  public Class() {}

  public Class(
      UUID id,
      String title,
      String videoPath,
      Boolean active,
      LocalDateTime createdAt,
      Module module) {
    this.id = id;
    this.title = title;
    this.videoPath = videoPath;
    this.active = active;
    this.createdAt = createdAt;
    this.module = module;
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

  public Module getModule() {
    return module;
  }

  public void setModule(Module module) {
    this.module = module;
  }
}
