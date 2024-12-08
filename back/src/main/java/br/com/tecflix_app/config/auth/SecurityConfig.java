package br.com.tecflix_app.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.tecflix_app.service.auth.api_key.AuthenticationFilter;
import br.com.tecflix_app.service.auth.jwt.SecurityFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    private final AuthenticationFilter authenticationFilter;
    private final SecurityFilter securityFilter;
    
    @Autowired
    public SecurityConfig(
        AuthenticationFilter authenticationFilter,
        SecurityFilter securityFilter
    ) {
        this.authenticationFilter = authenticationFilter;
        this.securityFilter = securityFilter;
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(
                authorize -> authorize
                    // hello-world
                    .requestMatchers(HttpMethod.GET, "/hello-world").permitAll()

                    // auth
                    .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                    .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                    .requestMatchers(HttpMethod.POST, "/auth/refresh-token").permitAll()
                    .requestMatchers(HttpMethod.POST, "/auth//send-code/{userId}").permitAll()
                    
                    // users
                    .requestMatchers(HttpMethod.POST, "/api/v1/users/{userId}/make-professor").hasAnyRole("ADMIN", "SUBSCRIBER", "PROFESSOR")
                    
                    // payment
                    .requestMatchers(HttpMethod.GET, "/api/v1/payments/pix").authenticated()
                    .requestMatchers(HttpMethod.POST, "/api/v1/payments/pix").authenticated()

                    .anyRequest().authenticated()
            )
            // api key filter
            .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
            // jwt filter
            .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration
    ) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }
}