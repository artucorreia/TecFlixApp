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
            SecurityFilter securityFilter) {
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
                                .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/auth/register").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/auth/refresh-token").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/auth/send-code/{userId}").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/auth/validate-code").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/auth/reset-password").permitAll()

                                // users
                                .requestMatchers(HttpMethod.GET, "/api/v1/users/find").permitAll()

                                // courses
                                .requestMatchers(HttpMethod.POST, "/api/v1/courses").hasAnyRole("ADMIN", "PROFESSOR")

                                // modules
                                .requestMatchers(HttpMethod.POST, "/api/v1/modules").hasAnyRole("ADMIN", "PROFESSOR")

                                // classes
                                .requestMatchers(HttpMethod.POST, "/api/v1/classes").hasAnyRole("ADMIN", "PROFESSOR")

                                // swagger
                                .requestMatchers("/v3/api-docs/**").permitAll()
                                .requestMatchers("/swagger-ui/**").permitAll()
                                .requestMatchers("/swagger-ui.html").permitAll()

                                .anyRequest().hasAnyRole("ADMIN", "USER", "PROFESSOR"))
                // api key filter
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                // jwt filter
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}