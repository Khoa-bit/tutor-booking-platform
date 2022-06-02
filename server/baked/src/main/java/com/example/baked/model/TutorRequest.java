package com.example.baked.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document
@AllArgsConstructor
@Data
public class TutorRequest {
  @Id private String id;
  @DocumentReference private List<AuthUser> requestingTutors;
  private Class requestClass;
}
