package br.com.tecflix_app.modules.course.infra.persistence;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

import br.com.tecflix_app.modules.shared.persistence.BaseEntity;
import br.com.tecflix_app.modules.tag.infra.persistence.TagEntity;
import br.com.tecflix_app.modules.user.infra.persistence.UserEntity;
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
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "courses")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class CourseEntity extends BaseEntity implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false, length = 100)
  private String title;

  @Column(nullable = false, length = 2000)
  private String description;

  @Column(name = "cape_image_url")
  private String capeImageUrl;

  @Column(name = "total_score", nullable = false)
  private Long totalScore;

  @Column(name = "total_reviews", nullable = false)
  private Long totalReviews;

  @Column(name = "average_score", nullable = false)
  private Double averageScore;

  @ManyToOne
  @JoinColumn(name = "professor_id", referencedColumnName = "id", nullable = false)
  private UserEntity professor;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "courses_students",
      joinColumns = @JoinColumn(name = "course_id"),
      inverseJoinColumns = @JoinColumn(name = "student_id"))
  private Set<UserEntity> students;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "courses_tags",
      joinColumns = @JoinColumn(name = "course_id"),
      inverseJoinColumns = @JoinColumn(name = "tag_id"))
  private Set<TagEntity> tags;
}
