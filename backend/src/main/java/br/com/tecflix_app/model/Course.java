package br.com.tecflix_app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "courses")
public class Course implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false, length = 40)
  private String title;

  @Column(name = "description", nullable = false, length = 2000)
  private String description;

  @Column(name = "cape_image")
  private String capeImage;

  @Column(nullable = false)
  private Boolean active;

  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @Column(name = "total_score")
  private Long totalScore;

  @Column(name = "total_reviews")
  private Long totalReviews;

  @Column(name = "average_score")
  private Double averageScore;

  @ManyToOne
  @JoinColumn(name = "professor_id", referencedColumnName = "id", nullable = false)
  private User professor;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "courses_students",
      joinColumns = @JoinColumn(name = "course_id"),
      inverseJoinColumns = @JoinColumn(name = "student_id"))
  private List<User> students;

  @OneToMany(mappedBy = "course")
  private List<Module> modules;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "courses_tags",
      joinColumns = @JoinColumn(name = "course_id"),
      inverseJoinColumns = @JoinColumn(name = "tag_id"))
  private List<Tag> tags;

  public Course() {}

  public Course(
      UUID id,
      String title,
      String description,
      String capeImage,
      Boolean active,
      LocalDateTime createdAt,
      Long totalScore,
      Long totalReviews,
      Double averageScore,
      User professor,
      List<User> students,
      List<Module> modules,
      List<Tag> tags) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.capeImage = capeImage;
    this.active = active;
    this.createdAt = createdAt;
    this.totalScore = totalScore;
    this.totalReviews = totalReviews;
    this.averageScore = averageScore;
    this.professor = professor;
    this.students = students;
    this.modules = modules;
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

  public String getCapeImage() {
    return capeImage;
  }

  public void setCapeImage(String capeImage) {
    this.capeImage = capeImage;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
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

  public Double getAverageScore() {
    return averageScore;
  }

  public void setAverageScore(Double averageScore) {
    this.averageScore = averageScore;
  }

  public List<User> getStudents() {
    return students;
  }

  public void setStudents(List<User> students) {
    this.students = students;
  }

  public List<Module> getModules() {
    return modules;
  }

  public void setModules(List<Module> modules) {
    this.modules = modules;
  }

  public List<Tag> getTags() {
    return tags;
  }

  public void setTags(List<Tag> tags) {
    this.tags = tags;
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
    Course other = (Course) obj;
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
