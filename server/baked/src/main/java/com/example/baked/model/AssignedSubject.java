package com.example.baked.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AssignedSubject {
  private SubjectName name;
  private Grade grades;
}
