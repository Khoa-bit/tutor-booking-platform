package com.example.baked.filter;

import com.example.baked.util.JWTUtil;
import com.example.baked.util.ResponseUtil;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
@RequiredArgsConstructor
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
  private final AuthenticationManager authenticationManager;
  private final JWTUtil jwtUtil;

  @Override
  public Authentication attemptAuthentication(
      HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    if (!request.getMethod().equals("POST")) {
      throw new AuthenticationServiceException(
          "Authentication method not supported: " + request.getMethod());
    }
    String username = request.getParameter("username");
    if (username == null || username.trim().equals("")) {
      throw new AuthenticationServiceException("Missing username");
    }

    String password = request.getParameter("password");
    if (password == null || password.trim().equals("")) {
      throw new AuthenticationServiceException("Missing password");
    }

    UsernamePasswordAuthenticationToken authentication =
        new UsernamePasswordAuthenticationToken(username, password);

    return authenticationManager.authenticate(authentication);
  }

  @Override
  protected void successfulAuthentication(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain chain,
      Authentication authResult) {
    SecurityContext context = SecurityContextHolder.createEmptyContext();
    context.setAuthentication(authResult);
    SecurityContextHolder.setContext(context);

    User user = (User) authResult.getPrincipal();
    String access_token = jwtUtil.generateAccessToken(user, request.getRequestURL().toString());
    String refresh_token = jwtUtil.generateRefreshToken(user, request.getRequestURL().toString());
    ResponseUtil.sendTokenCookies(response, access_token, refresh_token);
    response.setStatus(HttpServletResponse.SC_OK);
  }

  @Override
  protected void unsuccessfulAuthentication(
      HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
      throws IOException {
    log.error("Authentication unsuccessful");
    ResponseUtil.sendError(
        response, HttpServletResponse.SC_UNAUTHORIZED, "UNAUTHORIZED", exception.getMessage());
  }
}
