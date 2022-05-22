package com.example.baked.config;

import com.example.baked.repo.UserRepo;
import com.example.baked.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBSeedConfig {

  @Bean
  CommandLineRunner seedRunner(UserRepo userRepo, UserService userService) {
    return args -> {
      // userRepo.deleteAll();

      // userService.saveAuthUser(
      // new AuthUser(
      // null, "alicesmith", "321", new ArrayList<>(), new Student("Alice", "Smith"),
      // null));
      // userService.saveAuthUser(
      // new AuthUser(
      // null, "bobsmith", "123", new ArrayList<>(), new Student("Bob", "Smith"),
      // null));
      // userService.addRoleToUser("alicesmith", Role.ROLE_STUDENT);
      // userService.addRoleToUser("bobsmith", Role.ROLE_STUDENT);

      // userService.saveAuthUser(
      // new AuthUser(
      // null, "liamthomson", "321", new ArrayList<>(), null, new Tutor("Liam",
      // "Thomson")));
      // userService.saveAuthUser(
      // new AuthUser(
      // null, "olivathomson", "123", new ArrayList<>(), null, new Tutor("Oliva",
      // "Thomson")));
      // userService.addRoleToUser("liamthomson", Role.ROLE_TUTOR);
      // userService.addRoleToUser("olivathomson", Role.ROLE_TUTOR);

      // System.out.println(
      // "==================================================================================");
    };
  }
}
