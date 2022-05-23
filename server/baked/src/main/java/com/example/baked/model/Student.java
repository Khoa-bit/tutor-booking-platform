package com.example.baked.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Student {
  //  studentId is the same with AuthUser's ID
  private String studentId;
  private List<String> classes;
  private List<String> periods;
}
