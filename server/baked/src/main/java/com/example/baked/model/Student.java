package com.example.baked.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@AllArgsConstructor
@Data
public class Student {
  @DocumentReference private List<Class> classes;
  @DocumentReference private List<Period> periods;
}
