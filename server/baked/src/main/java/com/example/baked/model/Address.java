package com.example.baked.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Address {
  private String province_city;
  private String ward_district;
  private String home_number;

  @Override
  public String toString() {
    return this.province_city + ", " + this.ward_district + ", " + this.home_number;
  }
}
