package br.com.tecflix_app.data.DTO.v1.create;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.tecflix_app.data.DTO.v1.response.UserDTO;
import br.com.tecflix_app.model.enums.Account;
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
public class CreateBackDataDTO {
    @JsonIgnore
    private UserDTO user;
    
    @NotNull
    @NotBlank
    @Size(max = 5)
    private String agency;

    @NotNull
    @NotBlank
    @Size(min = 8, max = 12)
    private String accountNumber;

    @NotNull
    private Character dv;

    @NotNull
    private Account account;
}
