package com.example.baked.model;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@AllArgsConstructor
@Data
public class Period {
  @Id private String id;
  private DayOfWeek day;
  private LocalDateTime startTime;
  private LocalDateTime endTime;

  private int hash;

  public Period(DayOfWeek day, LocalDateTime startTime, LocalDateTime endTime) {
    this.id = null;
    this.day = day;
    this.startTime = startTime;
    this.endTime = endTime;
    this.hash = Objects.hash(day.getValue(), startTime, endTime);
  }
}
