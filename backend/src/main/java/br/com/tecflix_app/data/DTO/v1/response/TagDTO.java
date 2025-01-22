package br.com.tecflix_app.data.DTO.v1.response;

import java.time.LocalDateTime;
import org.springframework.hateoas.RepresentationModel;

public class TagDTO extends RepresentationModel<TagDTO> {
  private Long id;
  private String name;
  private Boolean active;
  private LocalDateTime createdAt;

  public TagDTO() {}

  public TagDTO(Long id, String name, Boolean active, LocalDateTime createdAt) {
    this.id = id;
    this.name = name;
    this.active = active;
    this.createdAt = createdAt;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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
