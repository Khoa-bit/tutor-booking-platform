package com.example.baked.controller;

import com.example.baked.model.AuthUser;
import com.example.baked.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sandbox")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearer-key")
public class SandboxController {
  private final UserService userService;

  @GetMapping("/users")
  public ResponseEntity<List<AuthUser>> getUsers() {
    return ResponseEntity.ok().body(userService.getUserMetadata());
  }
}
