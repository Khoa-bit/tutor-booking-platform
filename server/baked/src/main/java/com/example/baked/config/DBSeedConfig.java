package com.example.baked.config;

import com.example.baked.model.*;
import com.example.baked.repo.UserRepo;
import com.example.baked.service.UserService;
import com.github.javafaker.Faker;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBSeedConfig {
  private final Faker faker = new Faker();

  @Bean
  CommandLineRunner seedRunner(UserRepo userRepo, UserService userService) {
    return args -> {
      userRepo.deleteAll();

      userService.saveAuthUser(
          this.genAuthStudent("alicesmith", "321", new ArrayList<>(List.of(Role.ROLE_STUDENT))));
      userService.saveAuthUser(
          this.genAuthStudent("bobsmith", "123", new ArrayList<>(List.of(Role.ROLE_STUDENT))));

      userService.saveAuthUser(
          this.genAuthTutor("liamthomson", "321", new ArrayList<>(List.of(Role.ROLE_TUTOR))));
      userService.saveAuthUser(
          this.genAuthTutor("olivathomson", "123", new ArrayList<>(List.of(Role.ROLE_TUTOR))));

      System.out.println(
          "==================================================================================");
    };
  }

  private AuthUser genAuthStudent(String username, String password, ArrayList<Role> roles) {
    return new AuthUser(null, username, password, roles, this.genStudentMetadata());
  }

  private AuthUser genAuthTutor(String username, String password, ArrayList<Role> roles) {
    return new AuthUser(null, username, password, roles, this.genTutorMetadata());
  }

  private UserMetadata genStudentMetadata() {
    return new UserMetadata(
        new FullName(faker.name().firstName(), faker.name().lastName()),
        faker.demographic().sex(),
        faker.date().birthday(),
        new Address(
            faker.address().cityName(),
            faker.address().streetAddress(),
            faker.address().buildingNumber()),
        new ArrayList<>(List.of(faker.internet().emailAddress())),
        new ArrayList<>(List.of(faker.phoneNumber().phoneNumber())),
        faker.lorem().paragraph(),
        new ArrayList<>(List.of(genRelative(), genRelative())),
        this.genRandomStudent(),
        null);
  }

  private UserMetadata genTutorMetadata() {
    return new UserMetadata(
        new FullName(faker.name().firstName(), faker.name().lastName()),
        faker.demographic().sex(),
        faker.date().birthday(),
        new Address(
            faker.address().cityName(),
            faker.address().streetAddress(),
            faker.address().buildingNumber()),
        new ArrayList<>(List.of(faker.internet().emailAddress())),
        new ArrayList<>(List.of(faker.phoneNumber().phoneNumber())),
        faker.lorem().paragraph(),
        new ArrayList<>(List.of(genRelative(), genRelative())),
        null,
        this.genRandomTutor());
  }

  private Student genRandomStudent() {
    return new Student(new ArrayList<>(), new ArrayList<>());
  }

  private Tutor genRandomTutor() {
    return new Tutor(
        faker.job().title(),
        faker.university().name(),
        faker.company().industry(),
        faker.options().option("Bachelor", "Engineering", "Master", "High school"),
        faker.random().nextInt(1990, 2020),
        new ArrayList<>(),
        new ArrayList<>(),
        faker.random().nextInt(1, 20) * 100000,
        new ArrayList<>(),
        new ArrayList<>());
  }

  private Relative genRelative() {
    return new Relative(
        faker.random().hex(4).toLowerCase(),
        faker.options().option("Mother", "Father", "Brother", "Sister"),
        new FullName(faker.name().firstName(), faker.name().lastName()),
        faker.demographic().sex(),
        faker.date().birthday(),
        new Address(
            faker.address().cityName(),
            faker.address().streetAddress(),
            faker.address().buildingNumber()),
        new ArrayList<>(List.of(faker.internet().emailAddress())),
        new ArrayList<>(List.of(faker.phoneNumber().phoneNumber())),
        faker.lorem().paragraph());
  }
}
