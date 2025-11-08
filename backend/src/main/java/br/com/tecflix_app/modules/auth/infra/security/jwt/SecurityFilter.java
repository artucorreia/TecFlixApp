package br.com.tecflix_app.modules.auth.infra.security.jwt;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

import br.com.tecflix_app.modules.auth.application.usecases.ValidateTokenUseCase;
import br.com.tecflix_app.modules.auth.infra.gateways.TokenAuth0Gateway;
import br.com.tecflix_app.modules.shared.exception.ExceptionResponse;
import br.com.tecflix_app.modules.user.application.domain.entity.User;
import br.com.tecflix_app.modules.user.application.usecases.FindUserByIdUseCase;
import br.com.tecflix_app.modules.user.infra.gateways.mapper.UserGatewaysMapper;
import br.com.tecflix_app.modules.user.infra.persistence.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.tecflix_app.modules.shared.exception.auth.InactiveUserException;
import br.com.tecflix_app.modules.auth.application.domain.exception.InvalidTokenException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {
  private final FindUserByIdUseCase findUserByIdUseCase;
  private final ValidateTokenUseCase validateTokenUseCase;
  private final UserGatewaysMapper userGatewaysMapper;

  @SuppressWarnings("null")
  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    try {
      String token = recoverToken(request);

      if (token != null) {
        UUID userId = validateTokenUseCase.execute(token);
        if (userId == null) throw new InvalidTokenException("Token inválido ou expirado");

        User user = findUserByIdUseCase.execute(userId);
        if (!user.getEmailVerified()) throw new InactiveUserException("O usuário ainda não verificou seu email");
        if (user.getDeleted()) throw new InactiveUserException("O usuário está inativo");

        UserEntity userEntity = userGatewaysMapper.map(user);
        CustomUserDetails customUserDetails = new CustomUserDetails(userEntity);

        UsernamePasswordAuthenticationToken authentication =
            new UsernamePasswordAuthenticationToken(
                customUserDetails.getUser(), null, customUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
      }

      filterChain.doFilter(request, response);
    } catch (InvalidTokenException | InactiveUserException ex) {
      handleException(request, response, ex);
    }
  }

  private String recoverToken(HttpServletRequest request) {
    String authHeader = request.getHeader("Authorization");
    if (authHeader == null) return null;
    return authHeader.replace("Bearer ", "");
  }

  private void handleException(
      HttpServletRequest request, HttpServletResponse response, RuntimeException ex)
      throws IOException {
    response.setStatus(HttpStatus.FORBIDDEN.value());
    response.setContentType("application/json; charset=UTF-8");
    response.setCharacterEncoding("UTF-8");

    ExceptionResponse exceptionResponse =
        ExceptionResponse.builder()
            .success(false)
            .message(ex.getMessage())
            .uri(request.getRequestURI())
            .code(HttpStatus.FORBIDDEN.value())
            .timestamp(LocalDateTime.now())
            .build();
    response
        .getWriter()
        .write(
            """
            {
                "success": %s,
                "message": "%s",
                "uri": "%s",
                "code": %d,
                "timestamp": "%s"
            }
            """
                .formatted(
                    exceptionResponse.getSuccess(),
                    exceptionResponse.getMessage(),
                    exceptionResponse.getUri(),
                    exceptionResponse.getCode(),
                    exceptionResponse.getTimestamp()));
  }
}
