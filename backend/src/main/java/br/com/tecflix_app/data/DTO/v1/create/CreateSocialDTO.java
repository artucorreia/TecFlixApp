package br.com.tecflix_app.data.DTO.v1.create;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.tecflix_app.data.DTO.v1.response.UserDTO;
import br.com.tecflix_app.model.enums.SocialName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class CreateSocialDTO {
    @JsonIgnore
    private UserDTO user;
    
    @NotNull
    private SocialName name;
    
    @NotNull
    @NotBlank
    @Size(min = 5, max = 255)
    private String url;
}
