package br.com.tecflix_app.modules.user.application.domain.entity;

import br.com.tecflix_app.modules.auth.infra.persistence.RefreshTokenEntity;
import br.com.tecflix_app.modules.course.application.domain.entity.Course;
import br.com.tecflix_app.modules.course.infra.persistence.CourseEntity;
import br.com.tecflix_app.modules.emailCode.application.domain.entity.EmailCode;
import br.com.tecflix_app.modules.emailCode.infra.persistence.EmailCodeEntity;
import br.com.tecflix_app.modules.professorData.application.domain.entity.ProfessorData;
import br.com.tecflix_app.modules.auth.domain.entity.RefreshToken;
import br.com.tecflix_app.modules.professorData.infra.persistence.ProfessorDataEntity;
import br.com.tecflix_app.modules.review.application.domain.entity.Review;
import br.com.tecflix_app.modules.review.infra.persistence.ReviewEntity;
import br.com.tecflix_app.modules.social.application.domain.entity.Social;
import br.com.tecflix_app.modules.social.infra.persistence.SocialEntity;
import br.com.tecflix_app.modules.user.application.domain.enums.Role;
import jakarta.persistence.*;

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
  private EmailCode emailCode;
  private List<Course> enrolledCourses;
  private ProfessorData professorData;
  private List<Social> socials;
  private List<Course> coursesTaught;
  private List<Review> reviews;

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
      EmailCode emailCode,
      List<Course> enrolledCourses,
      ProfessorData professorData,
      List<Social> socials,
      List<Course> coursesTaught,
      List<Review> reviews) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.password = password;
    this.role = role;
    this.createdAt = createdAt;
    this.active = active;
    this.refreshToken = refreshToken;
    this.emailCode = emailCode;
    this.enrolledCourses = enrolledCourses;
    this.professorData = professorData;
    this.socials = socials;
    this.coursesTaught = coursesTaught;
    this.reviews = reviews;
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

  public EmailCode getEmailCode() {
    return emailCode;
  }

  public void setEmailCode(EmailCode emailCode) {
    this.emailCode = emailCode;
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

  public List<Review> getReviews() {
    return reviews;
  }

  public void setReviews(List<Review> reviews) {
    this.reviews = reviews;
  }
}
