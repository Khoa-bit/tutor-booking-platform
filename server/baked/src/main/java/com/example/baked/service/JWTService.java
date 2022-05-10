package com.example.baked.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public class JWTService {
  private final String JWT_SECRET;

  private final Algorithm ALGORITHM;

  public JWTService(@Value("${jwt.secret}") String JWT_SECRET) {
    this.JWT_SECRET = JWT_SECRET;
    this.ALGORITHM = Algorithm.HMAC256(JWT_SECRET.getBytes());
  }

  public String generateAccessToken(User user, String issuer) {
    return JWT.create()
        .withSubject(user.getUsername())
        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
        .withIssuer(issuer)
        .withClaim(
            "roles",
            user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()))
        .sign(ALGORITHM);
  }

  public String generateAccessToken(String username, Map<String, Claim> claims, String issuer) {
    return JWT.create()
        .withSubject(username)
        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
        .withIssuer(issuer)
        .withClaim("roles", claims.get("roles").asList(String.class))
        .sign(ALGORITHM);
  }

  public String generateRefreshToken(User user, String issuer) {
    return JWT.create()
        .withSubject(user.getUsername())
        .withExpiresAt(new Date(System.currentTimeMillis() + 30L * 24 * 60 * 60 * 1000))
        .withIssuer(issuer)
        .withClaim(
            "roles",
            user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()))
        .sign(ALGORITHM);
  }

  public DecodedJWT decodeJWT(String token) {
    Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET.getBytes());
    JWTVerifier verifier = JWT.require(algorithm).build();
    return verifier.verify(token);
  }
}
