package com.example.baked.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@AllArgsConstructor
@Data
public class Period {
  private String period_id;
  private String tutor_id;
  private String student_id;
  private int start_time;
  private int end_time;
  private int day;
}
