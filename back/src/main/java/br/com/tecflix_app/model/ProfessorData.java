package br.com.tecflix_app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

import br.com.tecflix_app.model.enums.Gender;
import br.com.tecflix_app.model.enums.Occupation;

@Table
@Entity(name = "professors_data")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@EqualsAndHashCode(of = "id")
public class ProfessorData implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(nullable = false, length = 11, unique = true)
    private String cpf;

    @Column(nullable = false)
    private LocalDate birthdate;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(length = 13, unique = true, nullable = false)
    private String contact;

    @Enumerated(EnumType.STRING)
    private Occupation occupation;

    @Column(length = 1000, nullable = false)
    private String biography;

    @Column(name = "profile_image", length = 255)
    private String profileImage;

    @Column(name = "created_at")
    private LocalDate createdAt;
}
