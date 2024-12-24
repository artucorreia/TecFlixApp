package br.com.tecflix_app.data.DTO.v1.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import br.com.tecflix_app.data.DTO.v1.create.CreateAddressDTO;
import br.com.tecflix_app.data.DTO.v1.create.CreateBackDataDTO;
import br.com.tecflix_app.data.DTO.v1.create.CreateProfessorDataDTO;
import br.com.tecflix_app.data.DTO.v1.create.CreateSocialDTO;
import br.com.tecflix_app.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter @Getter
public class UserDTO {
    private UUID id;
    private String name;
    private String email;
    private Role role;
    private LocalDateTime createdAt;
    private Boolean active;
    private CreateProfessorDataDTO professorData;
    private CreateAddressDTO address;
    private CreateBackDataDTO bankData;
    private List<CreateSocialDTO> socials;
}