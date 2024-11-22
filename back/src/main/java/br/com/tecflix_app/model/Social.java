package br.com.tecflix_app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

import br.com.tecflix_app.model.enums.SocialName;

@Table
@Entity(name = "socials")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@EqualsAndHashCode(of = "id")
public class Social implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private SocialName name;
    
    @Column(nullable = false, length = 255)
    private String url;

    @ManyToOne 
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
