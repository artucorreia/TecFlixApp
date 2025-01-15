package br.com.tecflix_app.data.DTO.v1.response;

import br.com.tecflix_app.model.enums.SocialName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SocialDTO {
    private Long id;
    private SocialName name;
    private String url;
}
