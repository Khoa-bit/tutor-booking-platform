package com.example.baked.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@AllArgsConstructor
@Data
public class StudentRequest {
  @Id private String id;
  private Class requestClass;
}
