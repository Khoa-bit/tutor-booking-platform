package com.example.baked.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Tutor {
  //  tutorId is the same with AuthUser's ID
  private String tutorId;
  private String job;
  private String graduatedSchool;
  private String major;
  private String qualification;
  private int graduatedYear;
  private List<String> grades;
  private List<String> subjects;
  private int minimumSalaryRequirement;
  private List<String> teachingClasses;
  private List<String> periods;
}
