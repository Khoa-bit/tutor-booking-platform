package com.example.baked.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.baked.util.JWTUtil;
import com.example.baked.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class JWTController {
  private final JWTUtil jwtUtil;

  @GetMapping("/ping")
  public ResponseEntity<Map<String, String>> ping(HttpServletRequest request, HttpServletResponse response) throws IOException {
    Map<String, String> body = new HashMap<>();
    body.put("message", "Valid Token");
    return ResponseEntity.ok().body(body);
  }

  @PostMapping("/token/refresh")
  public void refreshToken(HttpServletRequest request, HttpServletResponse response)
    throws IOException {
    String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
      try {
        String refresh_token = authorizationHeader.substring("Bearer ".length());
        DecodedJWT decodedJWT = jwtUtil.decodeJWT(refresh_token);
        if (!jwtUtil.isRefreshToken(decodedJWT.getClaims())) {
          ResponseUtil.sendError(
            response,
            HttpServletResponse.SC_UNAUTHORIZED,
            "UNAUTHORIZED",
            "Invalid refresh token");
          return;
        }
        String username = decodedJWT.getSubject();
        String access_token =
          jwtUtil.generateAccessToken(
            username, decodedJWT.getClaims(), request.getRequestURL().toString());

        ResponseUtil.sendTokens(response, access_token, refresh_token);
      } catch (Exception e) {
        ResponseUtil.sendError(
          response, HttpServletResponse.SC_UNAUTHORIZED, "UNAUTHORIZED", e.getMessage());
      }
    } else {
      ResponseUtil.sendError(
        response,
        HttpServletResponse.SC_UNAUTHORIZED,
        "UNAUTHORIZED",
        "Refresh token is missing");
    }
  }
}
