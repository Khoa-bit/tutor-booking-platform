package com.example.baked.controller;

import com.example.baked.model.AuthUser;
import com.example.baked.service.UserService;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  @GetMapping("/users")
  public ResponseEntity<List<AuthUser>> getUsers() {
    return ResponseEntity.ok().body(userService.getAppUsers());
  }

  @PostMapping("/users")
  public ResponseEntity<AuthUser> saveUser(
      @RequestBody AuthUser authUser, HttpServletResponse response) throws IOException {
    URI uri =
        URI.create(
            ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/user/save")
                .toUriString());
    return ResponseEntity.created(uri).body(userService.saveAuthUser(authUser));
  }
}
