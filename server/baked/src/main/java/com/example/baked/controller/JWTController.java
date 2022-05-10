package com.example.baked.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.baked.service.JWTService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class JWTController {
  private final JWTService jwtService;

  @PostMapping("/token/refresh")
  public void refreshToken(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
      try {
        String refresh_token = authorizationHeader.substring("Bearer ".length());
        DecodedJWT decodedJWT = jwtService.decodeJWT(refresh_token);
        String username = decodedJWT.getSubject();
        decodedJWT.getClaims();

        HashMap<String, String> tokens = new HashMap<>();
        tokens.put(
            "access_token",
            jwtService.generateAccessToken(
                username, decodedJWT.getClaims(), request.getRequestURL().toString()));
        tokens.put("refresh_token", refresh_token);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);

      } catch (Exception e) {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        HashMap<String, String> error = new HashMap<>();
        error.put("error_message", e.getMessage());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), error);
      }
    } else {
      throw new RuntimeException("Refresh token is missing");
    }
  }
}
