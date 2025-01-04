package br.com.tecflix_app.data.DTO.v1.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class ReviewDTO {
    private Long id;
    private Integer score;
    private String comment;
    private LocalDateTime createdAt;
    private UserDTO user;
}
