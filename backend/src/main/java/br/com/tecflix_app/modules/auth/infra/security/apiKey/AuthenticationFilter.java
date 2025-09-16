package br.com.tecflix_app.modules.auth.infra.security.apiKey;

import java.io.IOException;
import java.time.LocalDateTime;

import br.com.tecflix_app.modules.shared.exception.ExceptionResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.tecflix_app.modules.auth.domain.exception.InvalidApiKeyException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

  private final AuthenticationService authenticationService;
  private final ObjectMapper objectMapper =
      new ObjectMapper()
          .registerModule(new JavaTimeModule())
          .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

  @Autowired
  public AuthenticationFilter(AuthenticationService authenticationService) {
    this.authenticationService = authenticationService;
  }

  @Override
  @SuppressWarnings("null")
  protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
    String path = request.getRequestURI();
    return path.startsWith("/swagger-ui")
        || path.startsWith("/v3/api-docs")
        || path.startsWith("/api-docs")
        || path.startsWith("/swagger-ui.html")
        || path.startsWith("/swagger-config");
  }

  @Override
  @SuppressWarnings("null")
  public void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws IOException, ServletException {

    HttpServletRequest httpRequest = (HttpServletRequest) request;
    HttpServletResponse httpResponse = (HttpServletResponse) response;

    try {
      Authentication authentication = authenticationService.getAuthentication(httpRequest);
      SecurityContextHolder.getContext().setAuthentication(authentication);
      filterChain.doFilter(request, response);
    } catch (InvalidApiKeyException e) {
      handleException(httpRequest, httpResponse, e);
    }
  }

  private void handleException(
      HttpServletRequest httpServletRequest,
      HttpServletResponse response,
      InvalidApiKeyException ex)
      throws IOException {
    response.setStatus(HttpStatus.UNAUTHORIZED.value());
    response.setContentType("application/json");
    ExceptionResponse exceptionResponse =
        ExceptionResponse.builder()
            .success(false)
            .message(ex.getMessage())
            .uri(httpServletRequest.getRequestURI())
            .code(HttpStatus.UNAUTHORIZED.value())
            .timestamp(LocalDateTime.now())
            .build();
    String json = objectMapper.writeValueAsString(exceptionResponse);
    response.getWriter().write(json);
  }
}
