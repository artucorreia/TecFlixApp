package br.com.tecflix_app.modules.course.application.domain.entity;

import br.com.tecflix_app.modules.shared.application.domain.entity.BaseDomainEntity;
import br.com.tecflix_app.modules.tag.application.domain.entity.Tag;
import br.com.tecflix_app.modules.user.application.domain.entity.User;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class Course extends BaseDomainEntity {
  private UUID id;
  private String title;
  private String description;
  private String capeImageUrl;
  private Long totalScore;
  private Long totalReviews;
  private Double averageScore;
  private Boolean approved;
  private User professor;
  private Set<User> students;
  private Set<Tag> tags;

  public Course() {}

  public Course(
      UUID createdBy,
      LocalDateTime createdAt,
      UUID updatedBy,
      LocalDateTime updatedAt,
      Boolean deleted,
      UUID id,
      String title,
      String description,
      String capeImageUrl,
      Long totalScore,
      Long totalReviews,
      Double averageScore,
      Boolean approved,
      User professor,
      Set<User> students,
      Set<Tag> tags) {
    super(createdBy, createdAt, updatedBy, updatedAt, deleted);
    this.id = id;
    this.title = title;
    this.description = description;
    this.capeImageUrl = capeImageUrl;
    this.totalScore = totalScore;
    this.totalReviews = totalReviews;
    this.averageScore = averageScore;
    this.approved = approved;
    this.professor = professor;
    this.students = students;
    this.tags = tags;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getCapeImageUrl() {
    return capeImageUrl;
  }

  public void setCapeImageUrl(String capeImageUrl) {
    this.capeImageUrl = capeImageUrl;
  }

  public Long getTotalScore() {
    return totalScore;
  }

  public void setTotalScore(Long totalScore) {
    this.totalScore = totalScore;
  }

  public Long getTotalReviews() {
    return totalReviews;
  }

  public void setTotalReviews(Long totalReviews) {
    this.totalReviews = totalReviews;
  }

  public Double getAverageScore() {
    return averageScore;
  }

  public void setAverageScore(Double averageScore) {
    this.averageScore = averageScore;
  }

  public Boolean getApproved() {
    return approved;
  }

  public void setApproved(Boolean approved) {
    this.approved = approved;
  }

  public User getProfessor() {
    return professor;
  }

  public void setProfessor(User professor) {
    this.professor = professor;
  }

  public Set<User> getStudents() {
    return students;
  }

  public void setStudents(Set<User> students) {
    this.students = students;
  }

  public Set<Tag> getTags() {
    return tags;
  }

  public void setTags(Set<Tag> tags) {
    this.tags = tags;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    Course course = (Course) o;
    return Objects.equals(id, course.id)
        && Objects.equals(title, course.title)
        && Objects.equals(description, course.description)
        && Objects.equals(capeImageUrl, course.capeImageUrl)
        && Objects.equals(totalScore, course.totalScore)
        && Objects.equals(totalReviews, course.totalReviews)
        && Objects.equals(averageScore, course.averageScore)
        && Objects.equals(approved, course.approved)
        && Objects.equals(professor, course.professor)
        && Objects.equals(students, course.students)
        && Objects.equals(tags, course.tags);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        super.hashCode(),
        id,
        title,
        description,
        capeImageUrl,
        totalScore,
        totalReviews,
        averageScore,
        approved,
        professor,
        students,
        tags);
  }
}
