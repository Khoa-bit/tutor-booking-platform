package com.example.baked.model;

import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Relative {
  private String relationship;
  private FullName fullname;
  private String gender;
  private Date dob;
  private Address address;
  private List<String> emails;
  private List<String> phones;
  private String about;
}
