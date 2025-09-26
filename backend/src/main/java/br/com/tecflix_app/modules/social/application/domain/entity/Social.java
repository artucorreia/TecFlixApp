package br.com.tecflix_app.modules.social.application.domain.entity;

import br.com.tecflix_app.modules.shared.application.domain.entity.BaseDomainEntity;
import br.com.tecflix_app.modules.socialName.application.domain.entity.SocialName;
import br.com.tecflix_app.modules.user.application.domain.entity.User;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Social extends BaseDomainEntity {
  private Long id;
  private SocialName socialName;
  private String url;
  private User user;

  public Social() {}

  public Social(
      UUID createdBy,
      LocalDateTime createdAt,
      UUID updatedBy,
      LocalDateTime updatedAt,
      Boolean deleted,
      Long id,
      SocialName socialName,
      String url,
      User user) {
    super(createdBy, createdAt, updatedBy, updatedAt, deleted);
    this.id = id;
    this.socialName = socialName;
    this.url = url;
    this.user = user;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public SocialName getSocialName() {
    return socialName;
  }

  public void setSocialName(SocialName socialName) {
    this.socialName = socialName;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Social social = (Social) o;
    return Objects.equals(id, social.id)
        && Objects.equals(socialName, social.socialName)
        && Objects.equals(url, social.url)
        && Objects.equals(user, social.user);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, socialName, url, user);
  }
}
