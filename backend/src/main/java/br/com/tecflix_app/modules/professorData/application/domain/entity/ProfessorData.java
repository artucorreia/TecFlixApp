package br.com.tecflix_app.modules.professorData.application.domain.entity;

import br.com.tecflix_app.modules.gender.application.domain.entity.Gender;
import br.com.tecflix_app.modules.occupation.application.domain.entity.Occupation;
import br.com.tecflix_app.modules.shared.application.domain.entity.BaseDomainEntity;
import br.com.tecflix_app.modules.user.application.domain.entity.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class ProfessorData extends BaseDomainEntity {
  private Long id;
  private User user;
  private Occupation occupation;
  private String otherOccupation;
  private String biography;
  private LocalDate birthdate;
  private Gender gender;
  private String otherGender;
  private String phoneNumber;
  private String profileImageUrl;

  public ProfessorData() {}

  public ProfessorData(
      UUID createdBy,
      LocalDateTime createdAt,
      UUID updatedBy,
      LocalDateTime updatedAt,
      Boolean deleted,
      Long id,
      User user,
      Occupation occupation,
      String otherOccupation,
      String biography,
      LocalDate birthdate,
      Gender gender,
      String otherGender,
      String phoneNumber,
      String profileImageUrl) {
    super(createdBy, createdAt, updatedBy, updatedAt, deleted);
    this.id = id;
    this.user = user;
    this.occupation = occupation;
    this.otherOccupation = otherOccupation;
    this.biography = biography;
    this.birthdate = birthdate;
    this.gender = gender;
    this.otherGender = otherGender;
    this.phoneNumber = phoneNumber;
    this.profileImageUrl = profileImageUrl;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Occupation getOccupation() {
    return occupation;
  }

  public void setOccupation(Occupation occupation) {
    this.occupation = occupation;
  }

  public String getOtherOccupation() {
    return otherOccupation;
  }

  public void setOtherOccupation(String otherOccupation) {
    this.otherOccupation = otherOccupation;
  }

  public String getBiography() {
    return biography;
  }

  public void setBiography(String biography) {
    this.biography = biography;
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

  public String getOtherGender() {
    return otherGender;
  }

  public void setOtherGender(String otherGender) {
    this.otherGender = otherGender;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getProfileImageUrl() {
    return profileImageUrl;
  }

  public void setProfileImageUrl(String profileImageUrl) {
    this.profileImageUrl = profileImageUrl;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    ProfessorData that = (ProfessorData) o;
    return Objects.equals(id, that.id)
        && Objects.equals(user, that.user)
        && Objects.equals(occupation, that.occupation)
        && Objects.equals(otherOccupation, that.otherOccupation)
        && Objects.equals(biography, that.biography)
        && Objects.equals(birthdate, that.birthdate)
        && Objects.equals(gender, that.gender)
        && Objects.equals(otherGender, that.otherGender)
        && Objects.equals(phoneNumber, that.phoneNumber)
        && Objects.equals(profileImageUrl, that.profileImageUrl);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        super.hashCode(),
        id,
        user,
        occupation,
        otherOccupation,
        biography,
        birthdate,
        gender,
        otherGender,
        phoneNumber,
        profileImageUrl);
  }
}
