package com.example.baked.filter;

import static com.example.baked.security.SecurityConfig.AUTH_LOGIN_PATTERN;
import static com.example.baked.security.SecurityConfig.AUTH_TOKEN_REFRESH_PATTERN;
import static java.util.Arrays.stream;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.baked.service.JWTService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@RequiredArgsConstructor
public class CustomAuthorizationFilter extends OncePerRequestFilter {
  private final JWTService jwtService;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    AntPathMatcher antPathMatcher = new AntPathMatcher();
    if (antPathMatcher.match(AUTH_LOGIN_PATTERN, request.getServletPath())
        || antPathMatcher.match(AUTH_TOKEN_REFRESH_PATTERN, request.getServletPath())) {
      log.info("Accessing /auth apis");
      filterChain.doFilter(request, response);
      return;
    }

    String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
      log.error("Missing Authorization Header");
      filterChain.doFilter(request, response);
      return;
    }

    try {
      String token = authorizationHeader.substring("Bearer ".length());
      DecodedJWT decodedJWT = jwtService.decodeJWT(token);
      String username = decodedJWT.getSubject();
      String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
      Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
      stream(roles)
          .forEach(
              role -> {
                authorities.add(new SimpleGrantedAuthority(role));
              });

      UsernamePasswordAuthenticationToken authenticationToken =
          new UsernamePasswordAuthenticationToken(username, null, authorities);
      SecurityContextHolder.getContext().setAuthentication(authenticationToken);
      filterChain.doFilter(request, response);
    } catch (Exception e) {
      log.error("Error logging in: {}", e.getMessage());
      response.setStatus(HttpServletResponse.SC_FORBIDDEN);

      HashMap<String, String> error = new HashMap<>();
      error.put("error_message", e.getMessage());
      response.setContentType(MediaType.APPLICATION_JSON_VALUE);
      new ObjectMapper().writeValue(response.getOutputStream(), error);
    }
  }
}
