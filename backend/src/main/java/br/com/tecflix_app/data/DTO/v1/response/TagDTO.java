package br.com.tecflix_app.data.DTO.v1.response;

import java.time.LocalDateTime;

import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class TagDTO extends RepresentationModel<TagDTO> {
    private Long id;
    private String name;
    private Boolean active;
    private LocalDateTime createdAt;
}
