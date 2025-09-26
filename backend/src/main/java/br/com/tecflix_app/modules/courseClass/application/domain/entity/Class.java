package br.com.tecflix_app.modules.courseClass.application.domain.entity;

import br.com.tecflix_app.modules.module.application.domain.entity.Module;
import br.com.tecflix_app.modules.shared.application.domain.entity.BaseDomainEntity;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Class extends BaseDomainEntity {
  private UUID id;
  private String title;
  private String videoUrl;
  private Module module;

  public Class() {}

  public Class(
      UUID createdBy,
      LocalDateTime createdAt,
      UUID updatedBy,
      LocalDateTime updatedAt,
      Boolean deleted,
      UUID id,
      String title,
      String videoUrl,
      Module module) {
    super(createdBy, createdAt, updatedBy, updatedAt, deleted);
    this.id = id;
    this.title = title;
    this.videoUrl = videoUrl;
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

  public String getVideoUrl() {
    return videoUrl;
  }

  public void setVideoUrl(String videoUrl) {
    this.videoUrl = videoUrl;
  }

  public Module getModule() {
    return module;
  }

  public void setModule(Module module) {
    this.module = module;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    Class aClass = (Class) o;
    return Objects.equals(id, aClass.id)
        && Objects.equals(title, aClass.title)
        && Objects.equals(videoUrl, aClass.videoUrl)
        && Objects.equals(module, aClass.module);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), id, title, videoUrl, module);
  }
}
