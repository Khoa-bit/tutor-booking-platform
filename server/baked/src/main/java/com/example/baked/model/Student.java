package com.example.baked.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Student {
  private List<String> classes;
  private List<String> periods;
}
