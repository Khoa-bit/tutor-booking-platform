package com.example.baked.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@AllArgsConstructor
@Data
public class RequestFromStudent {
  @Id private String id;
  private String tutor_id;
  private String student_id;
  private String class_id;
  private String grade;
  private List<String> subjects;
  private Address address;
  private int salary;
  private String requirement;
  private List<String> periods;
}
