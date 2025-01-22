package br.com.tecflix_app.data.DTO.v1.response;

import br.com.tecflix_app.model.enums.Role;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class UserDTO {
  private UUID id;
  private String name;
  private String email;
  private Role role;
  private LocalDateTime createdAt;
  private Boolean active;
  private List<CourseDTO> enrolledCourses;
  private ProfessorDataDTO professorData;
  private List<SocialDTO> socials;
  private List<CourseDTO> coursesTaught;

  // private List<Payment> payments;

  public UserDTO() {}

  public UserDTO(UUID id) {
    this.id = id;
  }

  public UserDTO(
      UUID id,
      String name,
      String email,
      Role role,
      LocalDateTime createdAt,
      Boolean active,
      List<CourseDTO> enrolledCourses,
      ProfessorDataDTO professorData,
      List<SocialDTO> socials,
      List<CourseDTO> coursesTaught) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.role = role;
    this.createdAt = createdAt;
    this.active = active;
    this.enrolledCourses = enrolledCourses;
    this.professorData = professorData;
    this.socials = socials;
    this.coursesTaught = coursesTaught;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public List<CourseDTO> getEnrolledCourses() {
    return enrolledCourses;
  }

  public void setEnrolledCourses(List<CourseDTO> enrolledCourses) {
    this.enrolledCourses = enrolledCourses;
  }

  public ProfessorDataDTO getProfessorData() {
    return professorData;
  }

  public void setProfessorData(ProfessorDataDTO professorData) {
    this.professorData = professorData;
  }

  public List<SocialDTO> getSocials() {
    return socials;
  }

  public void setSocials(List<SocialDTO> socials) {
    this.socials = socials;
  }

  public List<CourseDTO> getCoursesTaught() {
    return coursesTaught;
  }

  public void setCoursesTaught(List<CourseDTO> coursesTaught) {
    this.coursesTaught = coursesTaught;
  }
}

