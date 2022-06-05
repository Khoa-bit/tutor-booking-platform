package com.example.baked.model;

import java.util.List;
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
  private List<Address> addresses;
  private List<TeachingSubject> teachingSubject;
  private int minimumSalaryRequirement;
  @DocumentReference private List<Period> periods;
  @DocumentReference private List<Period> occupiedPeriods;
}
