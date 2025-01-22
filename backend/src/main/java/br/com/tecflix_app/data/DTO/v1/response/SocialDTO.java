package br.com.tecflix_app.data.DTO.v1.response;

import br.com.tecflix_app.model.enums.SocialName;

public class SocialDTO {
  private Long id;
  private SocialName name;
  private String url;

  public SocialDTO() {}

  public SocialDTO(Long id, SocialName name, String url) {
    this.id = id;
    this.name = name;
    this.url = url;
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
}
