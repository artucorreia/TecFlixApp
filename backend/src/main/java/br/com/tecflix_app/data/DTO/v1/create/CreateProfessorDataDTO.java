package br.com.tecflix_app.data.DTO.v1.create;

import br.com.tecflix_app.data.DTO.v1.response.UserDTO;
import br.com.tecflix_app.model.enums.Gender;
import br.com.tecflix_app.model.enums.Occupation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class CreateProfessorDataDTO {
  @JsonIgnore private UserDTO user;

  @NotNull private LocalDate birthdate;

  @NotNull private Gender gender;

  @NotNull
  @NotBlank
  @Size(min = 13, max = 13)
  private String contact;

  @NotNull private Occupation occupation;

  @NotNull
  @Size(min = 10, max = 1000)
  private String biography;

  @Size(max = 255)
  private String profileImage;

  @JsonIgnore private LocalDateTime createdAt;

  public CreateProfessorDataDTO() {}

  public CreateProfessorDataDTO(
      UserDTO user,
      @NotNull LocalDate birthdate,
      @NotNull Gender gender,
      @NotNull @NotBlank @Size(min = 13, max = 13) String contact,
      @NotNull Occupation occupation,
      @NotNull @Size(min = 10, max = 1000) String biography,
      @Size(max = 255) String profileImage,
      LocalDateTime createdAt) {
    this.user = user;
    this.birthdate = birthdate;
    this.gender = gender;
    this.contact = contact;
    this.occupation = occupation;
    this.biography = biography;
    this.profileImage = profileImage;
    this.createdAt = createdAt;
  }

  public UserDTO getUser() {
    return user;
  }

  public void setUser(UserDTO user) {
    this.user = user;
  }

  public LocalDate getBirthdate() {
    return birthdate;
  }

  public void setBirthdate(LocalDate birthdate) {
    this.birthdate = birthdate;
  }

  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  public String getContact() {
    return contact;
  }

  public void setContact(String contact) {
    this.contact = contact;
  }

  public Occupation getOccupation() {
    return occupation;
  }

  public void setOccupation(Occupation occupation) {
    this.occupation = occupation;
  }

  public String getBiography() {
    return biography;
  }

  public void setBiography(String biography) {
    this.biography = biography;
  }

  public String getProfileImage() {
    return profileImage;
  }

  public void setProfileImage(String profileImage) {
    this.profileImage = profileImage;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }
}
