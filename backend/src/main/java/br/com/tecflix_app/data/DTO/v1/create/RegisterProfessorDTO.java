package br.com.tecflix_app.data.DTO.v1.create;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public class RegisterProfessorDTO {
  @NotNull @Valid private CreateProfessorDataDTO professorData;

  @Valid private List<CreateSocialDTO> socials;

  @JsonIgnore private LocalDateTime createdAt = LocalDateTime.now();

  public RegisterProfessorDTO() {}

  public RegisterProfessorDTO(
      @NotNull @Valid CreateProfessorDataDTO professorData,
      @Valid List<CreateSocialDTO> socials,
      LocalDateTime createdAt) {
    this.professorData = professorData;
    this.socials = socials;
    this.createdAt = createdAt;
  }

  public CreateProfessorDataDTO getProfessorData() {
    return professorData;
  }

  public void setProfessorData(CreateProfessorDataDTO professorData) {
    this.professorData = professorData;
  }

  public List<CreateSocialDTO> getSocials() {
    return socials;
  }

  public void setSocials(List<CreateSocialDTO> socials) {
    this.socials = socials;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }
}
