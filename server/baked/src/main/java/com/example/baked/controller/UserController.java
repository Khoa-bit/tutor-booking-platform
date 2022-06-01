package com.example.baked.controller;

import com.example.baked.controller.error.NotFoundException;
import com.example.baked.model.AuthUser;
import com.example.baked.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearer-key")
public class UserController {
  private final UserService userService;

  @GetMapping("/users")
  public ResponseEntity<List<AuthUser>> getUsers() {
    return ResponseEntity.ok().body(userService.getUserMetadata());
  }

  @GetMapping("/users/id/{id}")
  public ResponseEntity<AuthUser> getUserById(@PathVariable String id) {
    return ResponseEntity.ok()
        .body(
            userService
                .getUserMetadataById(id)
                .orElseThrow(() -> new NotFoundException("Invalid User ID")));
  }

  @GetMapping("/users/{username}")
  public ResponseEntity<AuthUser> getUserByUsername(@PathVariable String username) {
    return ResponseEntity.ok()
        .body(
            userService
                .getUserMetadataByUsername(username)
                .orElseThrow(() -> new NotFoundException("Invalid User Username")));
  }

  @GetMapping("/tutors")
  public ResponseEntity<List<AuthUser>> getTutors() {
    return ResponseEntity.ok().body(userService.getAllTutorMetadata());
  }

  @GetMapping("/tutors/id/{id}")
  public ResponseEntity<AuthUser> getTutorsById(@PathVariable String id) {
    return ResponseEntity.ok()
        .body(
            userService
                .getTutorMetadataById(id)
                .orElseThrow(() -> new NotFoundException("Invalid Tutor ID")));
  }

  @GetMapping("/tutors/{username}")
  public ResponseEntity<AuthUser> getTutorByUsername(@PathVariable String username) {
    return ResponseEntity.ok()
        .body(
            userService
                .getTutorMetadataByUsername(username)
                .orElseThrow(() -> new NotFoundException("Invalid Tutor Username")));
  }

  @GetMapping("/students")
  public ResponseEntity<List<AuthUser>> getStudents() {
    return ResponseEntity.ok().body(userService.getAllStudentMetadata());
  }

  @GetMapping("/students/id/{id}")
  public ResponseEntity<AuthUser> getStudentById(@PathVariable String id) {
    return ResponseEntity.ok()
        .body(
            userService
                .getStudentMetadataById(id)
                .orElseThrow(() -> new NotFoundException("Invalid Student ID")));
  }

  @GetMapping("/students/{username}")
  public ResponseEntity<AuthUser> getStudentByUsername(@PathVariable String username) {
    return ResponseEntity.ok()
        .body(
            userService
                .getStudentMetadataByUsername(username)
                .orElseThrow(() -> new NotFoundException("Invalid Student Username")));
  }

  @PostMapping("/users")
  public ResponseEntity<AuthUser> saveUser(@RequestBody AuthUser authUser) {
    URI uri =
        URI.create(
            ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/user/save")
                .toUriString());
    return ResponseEntity.created(uri).body(userService.saveAuthUser(authUser));
  }

  @GetMapping("/.../{city}+{subject}+{grade}") // Path Unfilled
  public ResponseEntity<List<AuthUser>> getTutorOnMainSearch(
      @PathVariable String city, @PathVariable String subject, @PathVariable String grade) {
    return ResponseEntity.ok().body(userService.getTutorOnMainSearch(city, subject, grade));
  }

  @GetMapping("/.../{city}+{subject}+{grade}+{district}") // Path Unfilled
  public ResponseEntity<List<AuthUser>> getTutorOnMainSearch2(
      @PathVariable String city,
      @PathVariable String subject,
      @PathVariable String grade,
      @PathVariable String district) {
    return ResponseEntity.ok()
        .body(userService.getTutorOnMainSearch2(city, subject, grade, district));
  }
}
