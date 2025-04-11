package com.java.fiap.users.config.security;

import com.java.fiap.users.application.dto.TokenValidationRequest;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.function.context.FunctionCatalog;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class TokenRevocationValidationFilter extends OncePerRequestFilter {

  private final FunctionCatalog functionCatalog;

  private static final List<RequestMatcher> PUBLIC_MATCHERS =
      List.of(
          new AntPathRequestMatcher("/swagger-ui/**"),
          new AntPathRequestMatcher("/v3/api-docs/**"),
          new AntPathRequestMatcher("/auth/**"));

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    // Primeiro deixe o filtro padrão processar a autenticação
    filterChain.doFilter(request, response);

    // Depois verifique se precisamos validar o token
    if (shouldNotValidate(request)) {
      return;
    }

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (!(authentication instanceof JwtAuthenticationToken)) {
      return;
    }

    JwtAuthenticationToken jwtAuth = (JwtAuthenticationToken) authentication;
    validateTokenRevocation(jwtAuth, response);
  }

  private boolean shouldNotValidate(HttpServletRequest request) {
    return PUBLIC_MATCHERS.stream().anyMatch(matcher -> matcher.matches(request));
  }

  private void validateTokenRevocation(JwtAuthenticationToken jwtAuth, HttpServletResponse response)
      throws IOException {

    String tokenId = jwtAuth.getToken().getId();
    TokenValidationRequest validationRequest =
        TokenValidationRequest.builder().jti(tokenId).build();

    Function<TokenValidationRequest, Boolean> validationFunction =
        functionCatalog.lookup("validateTokenRevocation");

    if (validationFunction != null && !validationFunction.apply(validationRequest)) {
      SecurityContextHolder.clearContext();
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      response.getWriter().write("Token has been revoked");
      response.flushBuffer();
    }
  }
}
