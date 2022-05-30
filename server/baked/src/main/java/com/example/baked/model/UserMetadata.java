package com.example.baked.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserMetadata {
  private FullName fullname;
  private String gender;
  private LocalDateTime dob;
  private Address address;
  private ArrayList<String> emails;
  private ArrayList<String> phones;
  private String about;
  private ArrayList<Relative> relatives;
  private Student student;
  private Tutor tutor;
}
