package com.example.baked.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.baked.controller.error.UnauthorizedException;
import com.example.baked.payload.request.LoginRequest;
import com.example.baked.util.JWTUtil;
import com.example.baked.util.ResponseUtil;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class JWTController {
  private final JWTUtil jwtUtil;
  private final AuthenticationManager authenticationManager;

  @GetMapping("/ping")
  public ResponseEntity<Map<String, String>> ping() {
    Map<String, String> body = new HashMap<>();
    body.put("message", "Valid Token");
    return ResponseEntity.ok().body(body);
  }

  @GetMapping("/user")
  public ResponseEntity<Map<String, String>> getCurrentUser(HttpServletRequest request) {
    UsernamePasswordAuthenticationToken authToken =
        (UsernamePasswordAuthenticationToken) request.getUserPrincipal();
    Map<String, String> body = new HashMap<>();
    body.put("Username", authToken.getName());
    body.put("Roles", String.valueOf(authToken.getAuthorities()));
    return ResponseEntity.ok().body(body);
  }

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(
      HttpServletRequest request,
      HttpServletResponse response,
      @RequestBody LoginRequest loginRequest) {

    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.username(), loginRequest.password()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    User user = (User) authentication.getPrincipal();
    String access_token = jwtUtil.generateAccessToken(user, request.getRequestURL().toString());
    String refresh_token = jwtUtil.generateRefreshToken(user, request.getRequestURL().toString());

    ResponseUtil.sendTokenCookies(response, access_token, refresh_token);

    Map<String, String> body =
        new HashMap<>(Map.of("access_token", access_token, "refresh_token", refresh_token));

    return ResponseEntity.ok(body);
  }

  @PostMapping("/token/refresh")
  public ResponseEntity<Map<String, String>> refreshToken(
      HttpServletRequest request, HttpServletResponse response) {
    String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
      try {
        String refresh_token = authorizationHeader.substring("Bearer ".length());
        DecodedJWT decodedJWT = jwtUtil.decodeJWT(refresh_token);
        if (!jwtUtil.isRefreshToken(decodedJWT.getClaims())) {
          throw new UnauthorizedException("Invalid refresh token");
        }
        String username = decodedJWT.getSubject();
        String access_token =
            jwtUtil.generateAccessToken(
                username, decodedJWT.getClaims(), request.getRequestURL().toString());

        ResponseUtil.sendTokenCookies(response, access_token, refresh_token);
        Map<String, String> body =
            new HashMap<>(Map.of("access_token", access_token, "refresh_token", refresh_token));
        return ResponseEntity.ok(body);
      } catch (Exception e) {
        throw new UnauthorizedException(e.getMessage());
      }
    } else {
      throw new UnauthorizedException("Refresh token is missing");
    }
  }
}
