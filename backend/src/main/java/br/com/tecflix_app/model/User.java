package br.com.tecflix_app.model;

import br.com.tecflix_app.model.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
public class User implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(length = 30, nullable = false)
  private String name;

  @Column(length = 50, unique = true, nullable = false)
  private String email;

  @Column(length = 100, nullable = false)
  private String password;

  @Enumerated(EnumType.STRING)
  private Role role;

  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @Column(nullable = false)
  private Boolean active;

  @OneToOne(mappedBy = "user")
  private RefreshToken refreshToken;

  @ManyToMany(mappedBy = "students")
  private List<Course> enrolledCourses;

  @OneToOne(mappedBy = "user")
  private ProfessorData professorData;

  @OneToMany(mappedBy = "user")
  private List<Social> socials;

  @OneToMany(mappedBy = "professor")
  private List<Course> coursesTaught;

  @OneToMany(mappedBy = "user")
  private List<Payment> payments;

  public User() {}

  public User(
      UUID id,
      String name,
      String email,
      String password,
      Role role,
      LocalDateTime createdAt,
      Boolean active,
      RefreshToken refreshToken,
      List<Course> enrolledCourses,
      ProfessorData professorData,
      List<Social> socials,
      List<Course> coursesTaught,
      List<Payment> payments) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.password = password;
    this.role = role;
    this.createdAt = createdAt;
    this.active = active;
    this.refreshToken = refreshToken;
    this.enrolledCourses = enrolledCourses;
    this.professorData = professorData;
    this.socials = socials;
    this.coursesTaught = coursesTaught;
    this.payments = payments;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    if (role == Role.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
    if (role == Role.PROFESSOR) return List.of(new SimpleGrantedAuthority("ROLE_PROFESSOR"));
    return List.of(new SimpleGrantedAuthority("ROLE_USER"));
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public RefreshToken getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(RefreshToken refreshToken) {
    this.refreshToken = refreshToken;
  }

  public List<Course> getEnrolledCourses() {
    return enrolledCourses;
  }

  public void setEnrolledCourses(List<Course> enrolledCourses) {
    this.enrolledCourses = enrolledCourses;
  }

  public ProfessorData getProfessorData() {
    return professorData;
  }

  public void setProfessorData(ProfessorData professorData) {
    this.professorData = professorData;
  }

  public List<Social> getSocials() {
    return socials;
  }

  public void setSocials(List<Social> socials) {
    this.socials = socials;
  }

  public List<Course> getCoursesTaught() {
    return coursesTaught;
  }

  public void setCoursesTaught(List<Course> coursesTaught) {
    this.coursesTaught = coursesTaught;
  }

  public List<Payment> getPayments() {
    return payments;
  }

  public void setPayments(List<Payment> payments) {
    this.payments = payments;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    User other = (User) obj;
    if (id == null) {
      if (other.id != null) {
        return false;
      }
    } else if (!id.equals(other.id)) {
      return false;
    }
    return true;
  }
}
