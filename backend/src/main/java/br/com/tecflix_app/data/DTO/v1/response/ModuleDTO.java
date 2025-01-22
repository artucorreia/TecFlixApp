package br.com.tecflix_app.data.DTO.v1.response;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.hateoas.RepresentationModel;

public class ModuleDTO extends RepresentationModel<ModuleDTO> {
  private Long id;
  private String title;
  private Boolean active;
  private LocalDateTime createdAt;
  private List<ClassDTO> classes;

  public ModuleDTO() {}

  public ModuleDTO(
      Long id, String title, Boolean active, LocalDateTime createdAt, List<ClassDTO> classes) {
    this.id = id;
    this.title = title;
    this.active = active;
    this.createdAt = createdAt;
    this.classes = classes;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
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

  public List<ClassDTO> getClasses() {
    return classes;
  }

  public void setClasses(List<ClassDTO> classes) {
    this.classes = classes;
  }
}
