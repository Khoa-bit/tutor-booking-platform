package com.example.baked.model;

import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document
@AllArgsConstructor
@Data
public class RequestFromStudent {
  @Id private String id;
  private String tutor_id;
  private String student_id;
  private String class_id;
  private Grade grade;
  @DocumentReference private Subject subject;
  private Address address;
  private int salary;
  private String requirement;
  private ArrayList<String> periods;
}
