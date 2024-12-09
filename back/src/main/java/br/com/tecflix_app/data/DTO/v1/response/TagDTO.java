package br.com.tecflix_app.data.DTO.v1.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class TagDTO {
    private Long id;
    private String name;
    // private List<CourseDTO> courses;
    private Boolean active;
    private LocalDateTime createdAt;
}
