package br.com.tecflix_app.data.DTO.v1.create;

import br.com.tecflix_app.data.DTO.v1.response.UserDTO;
import br.com.tecflix_app.model.enums.SocialName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateSocialDTO {
  @JsonIgnore private UserDTO user;

  @NotNull private SocialName name;

  @NotNull
  @NotBlank
  @Size(min = 5, max = 255)
  private String url;

  public CreateSocialDTO() {}

  public CreateSocialDTO(
      UserDTO user,
      @NotNull SocialName name,
      @NotNull @NotBlank @Size(min = 5, max = 255) String url) {
    this.user = user;
    this.name = name;
    this.url = url;
  }

  public UserDTO getUser() {
    return user;
  }

  public void setUser(UserDTO user) {
    this.user = user;
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
