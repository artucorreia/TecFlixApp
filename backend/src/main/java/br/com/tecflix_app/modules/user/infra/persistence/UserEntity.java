package br.com.tecflix_app.modules.user.infra.persistence;

import br.com.tecflix_app.modules.course.infra.persistence.CourseEntity;
import br.com.tecflix_app.modules.emailCode.infra.persistence.EmailCodeEntity;
import br.com.tecflix_app.modules.professorData.infra.persistence.ProfessorDataEntity;
import br.com.tecflix_app.modules.refreshToken.infra.persistence.RefreshTokenEntity;
import br.com.tecflix_app.modules.review.infra.persistence.ReviewEntity;
import br.com.tecflix_app.modules.social.infra.persistence.SocialEntity;
import br.com.tecflix_app.modules.user.application.domain.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(of = "id")
@Builder
public class UserEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(length = 30, nullable = false)
  private String name;

  @Column(length = 50, unique = true, nullable = false)
  private String email;

  @Column(length = 100, nullable = false)
  private String password;

  @Enumerated(EnumType.STRING)
  private Role role;

  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @Column(nullable = false)
  private Boolean active;

  @OneToOne(mappedBy = "user")
  private RefreshTokenEntity refreshToken;

  @OneToOne(mappedBy = "user")
  private EmailCodeEntity emailCode;

  @ManyToMany(mappedBy = "students")
  private List<CourseEntity> enrolledCourses;

  @OneToOne(mappedBy = "user")
  private ProfessorDataEntity professorData;

  @OneToMany(mappedBy = "user")
  private List<SocialEntity> socials;

  @OneToMany(mappedBy = "professor")
  private List<CourseEntity> coursesTaught;

  @OneToMany(mappedBy = "user")
  private List<ReviewEntity> reviews;
}
