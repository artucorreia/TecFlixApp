package br.com.tecflix_app.data.DTO.v1.create;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.tecflix_app.data.DTO.v1.response.UserDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class CreateAddressDTO {
    @JsonIgnore
    private UserDTO user;
    
    @NotNull
    @NotBlank
    @Size(min = 8, max = 8)
    private String cep;
    
    @NotNull
    @NotBlank
    @Size(min = 1, max = 8)
    private String number;
    
    @NotNull
    @NotBlank
    @Size(min = 1, max = 50)
    private String street;
    
    @NotNull
    @NotBlank
    @Size(min = 4, max = 20)
    private String city;
    
    @NotNull
    @NotBlank
    @Size(min = 4, max = 20)
    private String state;
    
    @NotNull
    @NotBlank
    @Size(max = 50)
    private String complement;
}
