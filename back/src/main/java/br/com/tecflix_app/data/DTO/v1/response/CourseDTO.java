package br.com.tecflix_app.data.DTO.v1.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class CourseDTO {
    private UUID id;
    private String title;
    private String description;
    private String capeImage;
    private Boolean active;
    private LocalDateTime createdAt;
    private Double totalScoreReviews;
    private Long totalReviews;
    private UserDTO user;
    private List<ModuleDTO> modules;
    private List<TagDTO> tags;
}
