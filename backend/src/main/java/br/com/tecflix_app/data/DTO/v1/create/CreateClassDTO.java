package br.com.tecflix_app.data.DTO.v1.create;

import br.com.tecflix_app.data.DTO.v1.response.ModuleDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

public class CreateClassDTO {
  @NotNull
  @NotBlank
  @Size(min = 5, max = 20)
  private String title;

  @NotNull
  @NotBlank
  @Size(max = 255)
  private String videoPath;

  @NotNull private ModuleDTO module;

  @JsonIgnore private Boolean active = true;

  @JsonIgnore private LocalDateTime createdAt = LocalDateTime.now();

  public CreateClassDTO() {}

  public CreateClassDTO(
      @NotNull @NotBlank @Size(min = 5, max = 20) String title,
      @NotNull @NotBlank @Size(max = 255) String videoPath,
      @NotNull ModuleDTO module,
      Boolean active,
      LocalDateTime createdAt) {
    this.title = title;
    this.videoPath = videoPath;
    this.module = module;
    this.active = active;
    this.createdAt = createdAt;
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

  public ModuleDTO getModule() {
    return module;
  }

  public void setModule(ModuleDTO module) {
    this.module = module;
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
