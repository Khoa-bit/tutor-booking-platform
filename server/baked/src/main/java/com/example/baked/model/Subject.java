package com.example.baked.model;

import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@AllArgsConstructor
@Data
public class Subject {
  @Id private String id;
  private SubjectName name;
  private ArrayList<Grade> grades;
}
