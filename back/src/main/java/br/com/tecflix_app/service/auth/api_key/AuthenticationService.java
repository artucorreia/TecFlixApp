package br.com.tecflix_app.service.auth.api_key;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import br.com.tecflix_app.exception.auth.InvalidApiKeyException;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class AuthenticationService {
    private static final String AUTH_TOKEN_HEADER_NAME = "X-API-KEY";
    
    @Value("${security.api.key}")
    private String authToken;
    
    public Authentication getAuthentication(HttpServletRequest request) {
        String apikey = request.getHeader(AUTH_TOKEN_HEADER_NAME);
        if (apikey == null || !apikey.equals(authToken)) {
            throw new InvalidApiKeyException("Invalid API KEY");
        }

        return new ApiKeyAuthentication(apikey, AuthorityUtils.NO_AUTHORITIES);
    }
}