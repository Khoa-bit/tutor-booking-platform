package com.example.baked.model;

import java.util.ArrayList;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@AllArgsConstructor
@Data
public class Tutor {
  private String job;
  private String graduatedSchool;
  private String major;
  private String qualification;
  private int graduatedYear;
  private ArrayList<Address> addresses;
  private Set<Grade> grades;
  @DocumentReference private Set<Subject> subjects;
  private int minimumSalaryRequirement;
  private ArrayList<String> teachingClasses;
  private ArrayList<String> periods;
}
