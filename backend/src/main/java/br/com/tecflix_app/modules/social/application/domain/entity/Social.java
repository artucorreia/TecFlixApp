package br.com.tecflix_app.modules.social.application.domain.entity;

import br.com.tecflix_app.modules.social.application.domain.enums.SocialName;
import br.com.tecflix_app.modules.user.application.domain.entity.User;

import java.io.Serializable;

public class Social implements Serializable {
  private Long id;
  private SocialName name;
  private String url;
  private User user;

  public Social() {}

  public Social(Long id, SocialName name, String url, User user) {
    this.id = id;
    this.name = name;
    this.url = url;
    this.user = user;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public SocialName getName() {
    return name;
  }

  public void setName(SocialName name) {
    this.name = name;
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
}
