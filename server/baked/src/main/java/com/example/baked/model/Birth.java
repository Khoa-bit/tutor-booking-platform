package com.example.baked.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Birth {
  private int day;
  private int month;
  private int year;

  @Override
  public String toString() {
    return this.day + "/" + this.month + "/" + this.year;
  }
}
