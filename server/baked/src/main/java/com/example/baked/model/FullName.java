package com.example.baked.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class FullName {
  private String first_name;
  private String last_name;

  @Override
  public String toString() {
    return this.last_name + " " + this.first_name;
  }
}
