package br.com.tecflix_app.modules.user.application.domain.entity;

import br.com.tecflix_app.modules.course.application.domain.entity.Course;
import br.com.tecflix_app.modules.professorData.application.domain.entity.ProfessorData;
import br.com.tecflix_app.modules.refreshToken.application.domain.entity.RefreshToken;
import br.com.tecflix_app.modules.social.application.domain.entity.Social;
import br.com.tecflix_app.modules.user.application.domain.enums.Role;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class User {
  private UUID id;
  private String name;
  private String email;
  private String password;
  private Role role;
  private LocalDateTime createdAt;
  private Boolean active;
  private RefreshToken refreshToken;
  private List<Course> enrolledCourses;
  private ProfessorData professorData;
  private List<Social> socials;
  private List<Course> coursesTaught;

  public User() {}

  public User(
      UUID id,
      String name,
      String email,
      String password,
      Role role,
      LocalDateTime createdAt,
      Boolean active,
      RefreshToken refreshToken,
      List<Course> enrolledCourses,
      ProfessorData professorData,
      List<Social> socials,
      List<Course> coursesTaught) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.password = password;
    this.role = role;
    this.createdAt = createdAt;
    this.active = active;
    this.refreshToken = refreshToken;
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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
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

  public RefreshToken getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(RefreshToken refreshToken) {
    this.refreshToken = refreshToken;
  }

  public List<Course> getEnrolledCourses() {
    return enrolledCourses;
  }

  public void setEnrolledCourses(List<Course> enrolledCourses) {
    this.enrolledCourses = enrolledCourses;
  }

  public ProfessorData getProfessorData() {
    return professorData;
  }

  public void setProfessorData(ProfessorData professorData) {
    this.professorData = professorData;
  }

  public List<Social> getSocials() {
    return socials;
  }

  public void setSocials(List<Social> socials) {
    this.socials = socials;
  }

  public List<Course> getCoursesTaught() {
    return coursesTaught;
  }

  public void setCoursesTaught(List<Course> coursesTaught) {
    this.coursesTaught = coursesTaught;
  }
}
