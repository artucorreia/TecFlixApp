package br.com.tecflix_app.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import br.com.tecflix_app.data.DTO.v1.auth.AuthenticationDTO;
import br.com.tecflix_app.data.DTO.v1.auth.TokenDTO;
import br.com.tecflix_app.exception.auth.InactiveUserException;
import br.com.tecflix_app.exception.auth.WrongPasswordException;
import br.com.tecflix_app.model.User;
import br.com.tecflix_app.service.UserService;
import br.com.tecflix_app.service.auth.jwt.TokenService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Autowired
    public AuthController(
        UserService userService,
        AuthenticationManager authenticationManager,
        TokenService tokenService
    ) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping(
        value = "/login",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<TokenDTO> login(@Valid @RequestBody AuthenticationDTO data) {
        if (!userService.findActiveByEmail(data.getEmail())) 
            throw new InactiveUserException("O usuário está inativo");

        UsernamePasswordAuthenticationToken usernamePassword =
            new UsernamePasswordAuthenticationToken(data.getEmail(), data.getPassword());

        TokenDTO token;
        try {
            Authentication auth = authenticationManager.authenticate(usernamePassword);
            token = tokenService.generateToken((User) auth.getPrincipal());
        }
        catch (AuthenticationException e){
            throw new WrongPasswordException("Senha incorreta");
        }

        return ResponseEntity.ok(token);
    }
}
