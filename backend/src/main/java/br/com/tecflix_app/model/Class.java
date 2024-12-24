package br.com.tecflix_app.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "classes")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@EqualsAndHashCode(of = "id")
public class Class implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 20)
    private String title;
    
    @Column(nullable = false, name = "video_path")
    private String videoPath;
    
    @Column(nullable = false)
    private Boolean active;

    @Column(nullable = false, name = "created_at", updatable = false)
    private LocalDateTime created_at;

    @ManyToOne 
    @JoinColumn(name = "module_id", referencedColumnName = "id", nullable = false)
    private Module module;
}