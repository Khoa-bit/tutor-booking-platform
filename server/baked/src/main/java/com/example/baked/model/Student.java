package com.example.baked.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Student")
@AllArgsConstructor
@Data
public class Student {
  private String student_id;
  private FullName fullname;
  private String gender;
  private Birth date_of_birth;
  private Address address;
  private FullName parent_name;
  private List<String> emails;
  private List<String> phones;
  private List<String> classes;
  private String about;
  private List<String> periods;
}
