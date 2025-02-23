package br.com.tecflix_app.data.DTO.v1.response;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class ModuleDTO extends RepresentationModel<ModuleDTO> {
    private Long id;
    private String title;
    private Boolean active;
    private LocalDateTime createdAt;
    private List<ClassDTO> classes;
}
