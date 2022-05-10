package com.example.baked.config;

import com.example.baked.model.AuthUser;
import com.example.baked.model.Role;
import com.example.baked.model.Student;
import com.example.baked.repo.StudentRepo;
import com.example.baked.repo.UserRepo;
import com.example.baked.service.UserService;
import java.util.ArrayList;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {

  @Bean
  CommandLineRunner commandLineRunner(
      StudentRepo studentRepo, UserRepo userRepo, UserService userService) {
    return args -> {
      userRepo.deleteAll();
      studentRepo.deleteAll();

      // save a couple of students
      userService.saveAppUser(
          new AuthUser(
              null, "alicesmith", "321", new ArrayList<>(), new Student("Alice", "Smith")));
      userService.saveAppUser(
          new AuthUser(null, "bobsmith", "123", new ArrayList<>(), new Student("Bob", "Smith")));
      userService.addRoleToUser("alicesmith", Role.ROLE_STUDENT);
      userService.addRoleToUser("bobsmith", Role.ROLE_STUDENT);

      // fetch all students
      System.out.println("Students found with findAll():");
      System.out.println("-------------------------------");
      for (Student student : studentRepo.findAll()) {
        System.out.println(student);
      }
      System.out.println();

      // fetch an individual student
      System.out.println("Student found with findByUsername('bobsmith'):");
      System.out.println("--------------------------------");
      System.out.println(userService.loadUserByUsername("bobsmith"));

      System.out.println("Student found with findByFirstName('Alice'):");
      System.out.println("--------------------------------");
      for (Student student : studentRepo.findByFirstname("Alice")) {
        System.out.println(student);
      }

      System.out.println("Students found with findByLastName('Smith'):");
      System.out.println("--------------------------------");
      for (Student student : studentRepo.findByLastname("Smith")) {
        System.out.println(student);
      }
    };
  }
}
