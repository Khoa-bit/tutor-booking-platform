package com.example.baked.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthUser {
  @Id private String id;
  private String username;
  private String password;
  private ArrayList<Role> roles;
  private FullName fullname;
  private String gender;
  private Date dob;
  private Address address;
  private List<String> emails;
  private List<String> phones;
  private String about;
  private List<Relative> relatives;
  private Student student;
  private Tutor tutor;
}
