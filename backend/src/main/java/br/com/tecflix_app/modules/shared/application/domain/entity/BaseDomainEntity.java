package br.com.tecflix_app.modules.shared.application.domain.entity;

import java.time.LocalDateTime;
import java.util.UUID;

public class BaseDomainEntity {
  private UUID createdBy;
  private LocalDateTime createdAt;
  private UUID updatedBy;
  private LocalDateTime updatedAt;
  private Boolean deleted;

  public BaseDomainEntity() {}

  public BaseDomainEntity(
      UUID createdBy,
      LocalDateTime createdAt,
      UUID updatedBy,
      LocalDateTime updatedAt,
      Boolean deleted) {
    this.createdBy = createdBy;
    this.createdAt = createdAt;
    this.updatedBy = updatedBy;
    this.updatedAt = updatedAt;
    this.deleted = deleted;
  }

  public UUID getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(UUID createdBy) {
    this.createdBy = createdBy;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public UUID getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(UUID updatedBy) {
    this.updatedBy = updatedBy;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  public Boolean getDeleted() {
    return deleted;
  }

  public void setDeleted(Boolean deleted) {
    this.deleted = deleted;
  }
}
