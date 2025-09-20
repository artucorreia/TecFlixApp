package br.com.tecflix_app.modules.professorData.infra.persistence;

import br.com.tecflix_app.modules.professorData.application.domain.enums.Gender;
import br.com.tecflix_app.modules.professorData.application.domain.enums.Occupation;
import br.com.tecflix_app.modules.user.infra.persistence.UserEntity;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "professors_data")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class ProfessorDataEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, unique = true)
  private UserEntity user;

  @Column(nullable = false, length = 11, unique = true)
  private String cpf;

  @Column(nullable = false)
  private LocalDate birthdate;

  @Enumerated(EnumType.STRING)
  private Gender gender;

  @Column(length = 13, unique = true, nullable = false)
  private String contact;

  @Enumerated(EnumType.STRING)
  private Occupation occupation;

  @Column(length = 2000, nullable = false)
  private String biography;

  @Column(name = "profile_image", length = 255)
  private String profileImage;

  @Column(name = "created_at", updatable = false)
  private LocalDateTime createdAt;
}
