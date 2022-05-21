package com.example.baked.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@Data
@Document(collection = "Tutor")
public class Tutor {
  private String tutor_id;
  private FullName fullname;
  private String gender;
  private Birth date_of_birth;
  private Address address;
  private List<String> emails;
  private List<String> phones;
  private String job;
  private String graduated_school;
  private String major;
  private String qualification;
  private int graduated_year;
  private List<String> grades;
  private List<String> subjects;
  private int minimum_salary_requirement;
  private List<String> teaching_classes;
  private String about;
  private List<String> periods;
}
