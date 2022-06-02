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
public class Class {
  @Id private String id;
  @DocumentReference private AuthUser tutor;
  @DocumentReference private AuthUser student;
  private AssignedSubject assignedSubject;
  private Address address;
  private int salary;
  private String requirement;
  @DocumentReference private ArrayList<Period> periods;
}
