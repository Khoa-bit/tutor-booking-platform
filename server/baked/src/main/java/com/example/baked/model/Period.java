package com.example.baked.model;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@Getter
@Setter
@ToString
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Period period = (Period) o;
    return hash == period.hash;
  }
}
