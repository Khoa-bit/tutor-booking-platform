package com.example.baked.controller;

import com.example.baked.model.AuthUser;
import com.example.baked.model.Role;
import com.example.baked.repo.UserRepo;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class TestController {
  private final UserRepo userRepo;

  ////////////////////////// Default Homepage //////////////////////////////
  @GetMapping(value = "/")
  public String homepage() {
    return "index.html";
  }

  ////////////////////////// Students //////////////////////////////
  @GetMapping(value = "api/test/getAllStudents")
  // @ResponseBody
  public String testGetAllStudents(Model model) {
    model.addAttribute("authStudents", userRepo.findAllByRolesContaining(Role.ROLE_STUDENT));
    return "test-controller/student";
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
    List<AuthUser> authTutors = userRepo.findAllByRolesContaining(Role.ROLE_TUTOR);

    List<String> province_cities = new ArrayList<>();
    List<String> ward_districts = new ArrayList<>();
    // List<Tutor> new_tutors = new ArrayList<>();
    List<AuthUser> new_tutors_id = new ArrayList<>();
    List<AuthUser> new_tutors_province_city = new ArrayList<>();
    List<AuthUser> new_tutors_ward_district = new ArrayList<>();

    for (AuthUser authTutor : authTutors) {
      province_cities.add(authTutor.getAddress().getProvince_city());
      ward_districts.add(authTutor.getAddress().getWard_district());
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
        for (AuthUser authTutor : authTutors) {
          if (id.equals("")) {
            new_tutors_id.add(authTutor);
          } else {
            if (id.equals(authTutor.getId())) {
              new_tutors_id.add(authTutor);
            }
          }

          if (province_city.equals("")) {
            new_tutors_province_city.add(authTutor);
          } else {
            if (province_city.equals(authTutor.getAddress().getProvince_city())) {
              new_tutors_province_city.add(authTutor);
            }
          }

          if (ward_district.equals("")) {
            new_tutors_ward_district.add(authTutor);
          } else {
            if (ward_district.equals(authTutor.getAddress().getWard_district())) {
              new_tutors_ward_district.add(authTutor);
            }
          }
        }

        authTutors.retainAll(new_tutors_id);
        authTutors.retainAll(new_tutors_province_city);
        System.out.println(new_tutors_province_city);
        authTutors.retainAll(new_tutors_ward_district);
      }
    }

    model.addAttribute("authTutors", authTutors);
    model.addAttribute("province_cities", province_cities);
    model.addAttribute("ward_districts", ward_districts);
    return "test-controller/tutor";
  }
}
