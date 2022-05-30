package com.example.baked.model;

import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Student {
  private ArrayList<String> classes;
  private ArrayList<String> periods;
}
