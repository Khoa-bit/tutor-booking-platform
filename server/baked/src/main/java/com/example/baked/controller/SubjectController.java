package com.example.baked.controller;

import com.example.baked.model.Subject;
import com.example.baked.service.SubjectService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearer-key")
public class SubjectController {
  private final SubjectService subjectService;

  @GetMapping("/subjects")
  public ResponseEntity<List<Subject>> getUsers() {
    return ResponseEntity.ok().body(subjectService.getAllSubjects());
  }
}
