package br.com.tecflix_app.controller;

import jakarta.validation.Valid;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import br.com.tecflix_app.data.DTO.v1.auth.AuthenticationDTO;
import br.com.tecflix_app.data.DTO.v1.auth.RefreshTokenDTO;
import br.com.tecflix_app.data.DTO.v1.auth.RegisterDTO;
import br.com.tecflix_app.data.DTO.v1.auth.TokenDTO;
import br.com.tecflix_app.data.DTO.v1.response.CreateResponseDTO;
import br.com.tecflix_app.exception.auth.InactiveUserException;
import br.com.tecflix_app.exception.auth.WrongPasswordException;
import br.com.tecflix_app.model.User;
import br.com.tecflix_app.service.UserService;
import br.com.tecflix_app.service.auth.jwt.RefreshTokenService;
import br.com.tecflix_app.service.auth.jwt.TokenService;

@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:4200"})
@RestController
@RequestMapping("/auth")
public class AuthController {
    
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final RefreshTokenService refreshTokenService;

    @Autowired
    public AuthController(
        UserService userService,
        AuthenticationManager authenticationManager,
        TokenService tokenService,
        RefreshTokenService refreshTokenService
    ) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.refreshTokenService = refreshTokenService;
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
            User user = (User) auth.getPrincipal();
            token = tokenService.generateToken(user.getId());
        }
        catch (AuthenticationException e){
            throw new WrongPasswordException("Senha incorreta");
        }

        return ResponseEntity.ok(token);
    }
    
    @PostMapping(
        value = "/refresh-token",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<TokenDTO> refreshToken(@Valid @RequestBody RefreshTokenDTO data) {
        UUID userId = refreshTokenService.resolve(data.getToken());        
        return ResponseEntity.ok(tokenService.generateToken(userId));
    }
    
    @PostMapping(
        value = "/register",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CreateResponseDTO<UUID>> register(@Valid @RequestBody RegisterDTO data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.register(data));
    }
}
