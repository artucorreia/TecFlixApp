package br.com.tecflix_app.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import br.com.tecflix_app.repository.UserRepository;

@Service
public class UserService {
    
    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {this.repository = repository;}
    
    public UserDetails findUserDetailsByEmail(String email) { 
        return repository.findUserDetailsByEmail(email);
    }
    
    public String findEmailById(UUID id) { return repository.findEmailById(id); }
    
    // public boolean findActiveById(UUID id) { return repository.findActiveById(id); }
    
    public boolean findActiveByEmail(String email) { return repository.findActiveByEmail(email); }
}
