package com.example.baked.controller;

import com.example.baked.model.Tutor;
import com.example.baked.repo.StudentRepository;
import com.example.baked.repo.TutorRepository;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TestController {
  @Autowired private TutorRepository tutorRepository;

  @Autowired private StudentRepository studentRepository;

  ////////////////////////// Default Homepage //////////////////////////////
  @GetMapping(value = "/")
  public String homepage() {
    return "index.html";
  }

  ////////////////////////// Students //////////////////////////////
  @GetMapping(value = "api/test/getAllStudents")
  // @ResponseBody
  public String testGetAllStudents(Model model) {
    model.addAttribute("students", studentRepository.findAll());
    return "test-controller/student.html";
    // return studentRepository.findAll();
  }

  ////////////////////////// Tutors //////////////////////////////
  @GetMapping(value = "api/test/getAllTutors")
  public String testGetAllTutors(
      Model model,
      @RequestParam(required = false) String id,
      @RequestParam(required = false) String province_city,
      @RequestParam(required = false) String ward_district) {

    // Retrive all tutors from database
    List<Tutor> tutors = tutorRepository.findAll();

    List<String> province_cities = new ArrayList<>();
    List<String> ward_districts = new ArrayList<>();
    // List<Tutor> new_tutors = new ArrayList<>();
    List<Tutor> new_tutors_id = new ArrayList<>();
    List<Tutor> new_tutors_province_city = new ArrayList<>();
    List<Tutor> new_tutors_ward_district = new ArrayList<>();

    for (Tutor tutor : tutors) {
      province_cities.add(tutor.getAddress().getProvince_city());
      ward_districts.add(tutor.getAddress().getWard_district());
    }

    // Remove Duplications of province_cities
    Set<String> set = new LinkedHashSet<>();
    set.addAll(province_cities);
    province_cities.clear();
    province_cities.addAll(set);

    // Remove Duplications of province_cities
    set.clear();
    set.addAll(ward_districts);
    ward_districts.clear();
    ward_districts.addAll(set);

    // Filter
    if (id != null) {
      if (!id.equals("") || !province_city.equals("") || !ward_district.equals("")) {
        for (Tutor tutor : tutors) {
          if (id.equals("")) {
            new_tutors_id.add(tutor);
          } else {
            if (id.equals(tutor.getTutor_id())) {
              new_tutors_id.add(tutor);
            }
          }

          if (province_city.equals("")) {
            new_tutors_province_city.add(tutor);
          } else {
            if (province_city.equals(tutor.getAddress().getProvince_city())) {
              new_tutors_province_city.add(tutor);
            }
          }

          if (ward_district.equals("")) {
            new_tutors_ward_district.add(tutor);
          } else {
            if (ward_district.equals(tutor.getAddress().getWard_district())) {
              new_tutors_ward_district.add(tutor);
            }
          }
        }

        tutors.retainAll(new_tutors_id);

        tutors.retainAll(new_tutors_province_city);
        System.out.println(new_tutors_province_city);
        tutors.retainAll(new_tutors_ward_district);
      }
    }

    model.addAttribute("tutors", tutors);
    model.addAttribute("province_cities", province_cities);
    model.addAttribute("ward_districts", ward_districts);
    return "test-controller/tutor.html";
  }
}
