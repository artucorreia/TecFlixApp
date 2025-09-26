package br.com.tecflix_app.modules.tag.application.domain.entity;

import br.com.tecflix_app.modules.shared.application.domain.entity.BaseDomainEntity;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Tag extends BaseDomainEntity {
  private Long id;
  private String name;

  public Tag() {}

  public Tag(
      UUID createdBy,
      LocalDateTime createdAt,
      UUID updatedBy,
      LocalDateTime updatedAt,
      Boolean deleted,
      Long id,
      String name) {
    super(createdBy, createdAt, updatedBy, updatedAt, deleted);
    this.id = id;
    this.name = name;
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

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    Tag tag = (Tag) o;
    return Objects.equals(id, tag.id) && Objects.equals(name, tag.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), id, name);
  }
}
