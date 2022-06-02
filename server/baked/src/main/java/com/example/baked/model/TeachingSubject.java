package com.example.baked.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TeachingSubject {
  private SubjectName name;
  private List<Grade> grades;
}
