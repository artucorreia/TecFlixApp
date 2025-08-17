package br.com.tecflix_app.modules.professorData.application.domain.entity;

import br.com.tecflix_app.modules.professorData.application.domain.enums.Gender;
import br.com.tecflix_app.modules.professorData.application.domain.enums.Occupation;
import br.com.tecflix_app.modules.user.application.domain.entity.User;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ProfessorData implements Serializable {
  private Long id;
  private User user;
  private String cpf;
  private LocalDate birthdate;
  private Gender gender;
  private String contact;
  private Occupation occupation;
  private String biography;
  private String profileImage;
  private LocalDateTime createdAt;

  public ProfessorData() {}

  public ProfessorData(
      Long id,
      User user,
      String cpf,
      LocalDate birthdate,
      Gender gender,
      String contact,
      Occupation occupation,
      String biography,
      String profileImage,
      LocalDateTime createdAt) {
    this.id = id;
    this.user = user;
    this.cpf = cpf;
    this.birthdate = birthdate;
    this.gender = gender;
    this.contact = contact;
    this.occupation = occupation;
    this.biography = biography;
    this.profileImage = profileImage;
    this.createdAt = createdAt;
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

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
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
