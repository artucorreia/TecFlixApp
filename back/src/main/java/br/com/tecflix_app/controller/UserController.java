package br.com.tecflix_app.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tecflix_app.data.DTO.v1.create.RegisterProfessorDTO;
import br.com.tecflix_app.data.DTO.v1.response.CreateResponseDTO;
import br.com.tecflix_app.data.DTO.v1.response.UserDTO;
import br.com.tecflix_app.service.UserService;
import jakarta.validation.Valid;

@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:4200"})
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(
        UserService userService
    ) {
        this.userService = userService;
    }

    @PostMapping(
        value = "/{userId}/make-professor",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public CreateResponseDTO<UUID> createProfessor(@PathVariable UUID userId, @Valid @RequestBody RegisterProfessorDTO data) {
        UserDTO user = new UserDTO();
        user.setId(userId);
        return userService.createProfessor(user, data);
    }

}
