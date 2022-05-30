package com.example.baked.model;

import java.util.ArrayList;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Relative {
  private String id;
  private String relationship;
  private FullName fullname;
  private String gender;
  private Date dob;
  private Address address;
  private ArrayList<String> emails;
  private ArrayList<String> phones;
  private String about;
}
