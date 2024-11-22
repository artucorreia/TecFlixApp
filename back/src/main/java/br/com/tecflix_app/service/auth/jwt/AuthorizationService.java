package br.com.tecflix_app.service.auth.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.tecflix_app.repository.UserRepository;

@Service
public class AuthorizationService implements UserDetailsService {

    private final UserRepository repository;

    @Autowired
    public AuthorizationService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findUserDetailsByEmail(username);
    }
    
}
