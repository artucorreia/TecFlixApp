package br.com.tecflix_app.modules.professorData.infra.persistence;

import br.com.tecflix_app.modules.gender.infra.persistence.GenderEntity;
import br.com.tecflix_app.modules.occupation.infra.persistence.OccupationEntity;
import br.com.tecflix_app.modules.shared.persistence.BaseEntity;
import br.com.tecflix_app.modules.user.infra.persistence.UserEntity;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
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
@EqualsAndHashCode(callSuper = true)
public class ProfessorDataEntity extends BaseEntity implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, unique = true)
  private UserEntity user;

  @ManyToOne
  @JoinColumn(name = "occupation_id", referencedColumnName = "id", nullable = false)
  private OccupationEntity occupation;

  @Column(name = "other_occupation", length = 50)
  private String otherOccupation;

  @Column(length = 2000, nullable = false)
  private String biography;

  @Column(nullable = false)
  private LocalDate birthdate;

  @ManyToOne
  @JoinColumn(name = "gender_id", referencedColumnName = "id", nullable = false)
  private GenderEntity gender;

  @Column(name = "other_gender", length = 30)
  private String otherGender;

  @Column(name = "phone_number", length = 13, unique = true, nullable = false)
  private String phoneNumber;

  @Column(name = "profile_image_url")
  private String profileImageUrl;
}
