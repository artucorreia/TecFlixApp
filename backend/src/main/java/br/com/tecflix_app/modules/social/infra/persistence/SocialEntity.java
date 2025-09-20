package br.com.tecflix_app.modules.social.infra.persistence;

import br.com.tecflix_app.modules.shared.persistence.BaseEntity;
import br.com.tecflix_app.modules.socialName.infra.persistence.SocialNameEntity;
import br.com.tecflix_app.modules.user.infra.persistence.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "socials")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class SocialEntity extends BaseEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "social_name_id", referencedColumnName = "id", nullable = false)
  private SocialNameEntity socialName;

  @Column(nullable = false)
  private String url;

  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
  private UserEntity user;
}
