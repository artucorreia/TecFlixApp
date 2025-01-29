package br.com.tecflix_app.model;

import br.com.tecflix_app.model.enums.Gender;
import br.com.tecflix_app.model.enums.Occupation;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Table
@Entity(name = "professors_data")
public class ProfessorData implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, unique = true)
  private User user;

  @Column(nullable = false)
  private LocalDate birthdate;

  @Enumerated(EnumType.STRING)
  private Gender gender;

  @Column(length = 25, unique = true, nullable = false)
  private String contact;

  @Enumerated(EnumType.STRING)
  private Occupation occupation;

  @Column(length = 2000, nullable = false)
  private String biography;

  @Column(name = "profile_image", length = 255)
  private String profileImage;

  @Column(name = "created_at", updatable = false)
  private LocalDateTime createdAt;

  public ProfessorData() {}

  public ProfessorData(
      final Long id,
      final User user,
      final LocalDate birthdate,
      final Gender gender,
      final String contact,
      final Occupation occupation,
      final String biography,
      final String profileImage,
      final LocalDateTime createdAt) {
    this.id = id;
    this.user = user;
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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    ProfessorData other = (ProfessorData) obj;
    if (id == null) {
      if (other.id != null) {
        return false;
      }
    } else if (!id.equals(other.id)) {
      return false;
    }
    return true;
  }
}
