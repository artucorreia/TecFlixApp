package br.com.tecflix_app.service.auth.api_key;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.tecflix_app.exception.auth.InvalidApiKeyException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {
    
    private final AuthenticationService authenticationService;
    
    @Autowired
    public AuthenticationFilter(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    @SuppressWarnings("null")
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        return path.startsWith("/swagger-ui") || path.startsWith("/v3/api-docs");
    }
    
    @Override
    @SuppressWarnings("null")
    public void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    ) throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        try {
            Authentication authentication = authenticationService.getAuthentication(httpRequest);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        } 
        catch (InvalidApiKeyException e) {
            handleException(httpResponse,e);
        }
    }
    
    private void handleException(
            HttpServletResponse response,
            InvalidApiKeyException e
    ) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");
        response.getWriter().write(
            "{\"timestamp\":\"" + LocalDateTime.now() + 
            "\",\"title\":\"" + e.getMessage() + 
            "\",\"details\":\"null or incorrect API KEY\"}"
        );
    }    
}