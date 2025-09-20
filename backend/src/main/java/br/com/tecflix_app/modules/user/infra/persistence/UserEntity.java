package br.com.tecflix_app.modules.user.infra.persistence;

import br.com.tecflix_app.modules.role.infra.persistence.RoleEntity;
import br.com.tecflix_app.modules.shared.persistence.BaseEntity;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
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
@EqualsAndHashCode(callSuper = true)
@Builder
public class UserEntity extends BaseEntity implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(length = 50, nullable = false)
  private String name;

  @Column(length = 60, unique = true, nullable = false)
  private String email;

  @Column(length = 100, nullable = false)
  private String password;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "users_roles",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<RoleEntity> roles;

  @Column(name = "email_verified", nullable = false)
  private Boolean emailVerified;

  @Column(name = "email_verified_at", insertable = false)
  private LocalDateTime emailVerifiedAt;
}
