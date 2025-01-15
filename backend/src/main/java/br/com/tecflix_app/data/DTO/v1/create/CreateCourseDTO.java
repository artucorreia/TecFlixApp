package br.com.tecflix_app.data.DTO.v1.create;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.tecflix_app.data.DTO.v1.response.TagDTO;
import br.com.tecflix_app.data.DTO.v1.response.UserDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateCourseDTO {
    @NotNull
    @NotBlank
    @Size(min = 5, max = 40)
    private String title;

    @NotNull
    @NotBlank
    @Size(min = 5, max = 2000)
    private String description;

    @Size(max = 255)
    private String capeImage;

    @NotEmpty
    private List<TagDTO> tags;

    @JsonIgnore
    private UserDTO professor;

    @JsonIgnore
    private Boolean active = true;

    @JsonIgnore
    private LocalDateTime createdAt = LocalDateTime.now();

    @JsonIgnore
    private Long totalScore = 0L;

    @JsonIgnore
    private Long totalReviews = 0L;

    @JsonIgnore
    private Double averageScore = 0D;
}
