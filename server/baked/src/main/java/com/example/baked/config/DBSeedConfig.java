package com.example.baked.config;

import com.example.baked.model.*;
import com.example.baked.repo.LocationRepo;
import com.example.baked.repo.PeriodRepo;
import com.example.baked.repo.SubjectRepo;
import com.example.baked.repo.UserRepo;
import com.example.baked.service.LocationService;
import com.example.baked.service.UserService;
import com.github.javafaker.Faker;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.SampleOperation;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class DBSeedConfig {
  private final Faker faker = new Faker();

  private final UserRepo userRepo;
  private final UserService userService;
  private final LocationRepo locationRepo;
  private final LocationService locationService;
  private final SubjectRepo subjectRepo;
  private final PeriodRepo periodRepo;
  private final MongoTemplate mongoTemplate;

  @Bean
  CommandLineRunner seedRunner() {
    return args -> {
      if (args.length == 0) return;
      for (String arg : args) {
        if (!arg.equals("clearDB")) return;
      }

      userRepo.deleteAll();
      locationRepo.deleteAll();
      subjectRepo.deleteAll();
      periodRepo.deleteAll();

      this.genPeriods();
      this.genLocations(locationService);
      this.genSubjects(subjectRepo);

      userService.saveAuthUser(
          this.genAuthStudent(
              "alicesmith@student.hometutor", "321", new HashSet<>(List.of(Role.ROLE_STUDENT))));
      userService.saveAuthUser(
          this.genAuthStudent(
              "bobsmith@student.hometutor", "123", new HashSet<>(List.of(Role.ROLE_STUDENT))));

      userService.saveAuthUser(
          this.genAuthTutor(
              "liamthomson@tutor.hometutor", "321", new HashSet<>(List.of(Role.ROLE_TUTOR))));
      userService.saveAuthUser(
          this.genAuthTutor(
              "olivathomson@tutor.hometutor", "123", new HashSet<>(List.of(Role.ROLE_TUTOR))));

      System.out.println(
          "==================================================================================");
    };
  }

  private void genPeriods() {
    LocalDateTime start = LocalDateTime.of(2000, 1, 1, 19, 0);
    ArrayList<Period> periods = new ArrayList<>();
    for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
      periods.add(new Period(dayOfWeek, start, start.plusHours(1).plusMinutes(30)));
    }
    periodRepo.saveAll(periods);
  }

  private List<Address> getRandomAddress(long size) {
    //    SampleOperation sampleStage = Aggregation.sample(1);
    //
    //    Aggregation aggregation
    //      = Aggregation.newAggregation(sampleStage);
    //
    //    List<Location> locations = mongoTemplate.aggregate(aggregation,
    // Location.class.getSimpleName().toLowerCase(), Location.class).getMappedResults();

    //    return locations.stream().map(location -> new Address(
    //      location.getProvinceCity(),
    //      faker.options().nextElement(location.getDistrict()),
    //      faker.address().streetAddress())).toList();

    Optional<Location> location = locationRepo.findByProvinceCity("Hồ Chí Minh city");

    List<Address> addresses = new ArrayList<>();
    for (long i = 0; i < size; i++) {
      location.ifPresent(
          value ->
              addresses.add(
                  new Address(
                      value.getProvinceCity(),
                      faker.options().nextElement(value.getDistrict()),
                      faker.address().streetAddress())));
    }
    return addresses;
  }

  private List<Subject> getRandomSubject(long size) {
    SampleOperation sampleStage = Aggregation.sample(size);

    Aggregation aggregation = Aggregation.newAggregation(sampleStage);

    return mongoTemplate
        .aggregate(aggregation, Subject.class.getSimpleName().toLowerCase(), Subject.class)
        .getMappedResults();
  }

  private void genSubjects(SubjectRepo subjectRepo) {
    ArrayList<Subject> subjects = new ArrayList<>();
    for (SubjectName name : SubjectName.values()) {
      subjects.add(new Subject(null, name, new ArrayList<>(List.of(Grade.values()))));
    }
    subjectRepo.saveAll(subjects);
  }

  private void genLocations(LocationService locationService) {
    locationService.saveLocation(
        new Location(
            null,
            "Hồ Chí Minh city",
            new ArrayList<>(
                List.of(
                    "Quận 1",
                    "Quận 2 - Thủ Đức city",
                    "Quận 3",
                    "Quận 4",
                    "Quận 5",
                    "Quận 6",
                    "Quận 7",
                    "Quận 8",
                    "Quận 9 - Thủ Đức city",
                    "Quận 10",
                    "Quận 11",
                    "Quận 12",
                    "Quận Bình Thạnh",
                    "Quận Bình Tân",
                    "Quận Gò Vấp",
                    "Quận Phú Nhuận",
                    "Quận Tân Bình",
                    "Quận Tân Phú",
                    "Huyện Thủ Đức",
                    "Huyện Hóc Môn",
                    "Huyện Bình Chánh",
                    "Huyện Nhà Bè",
                    "Huyện Cần Giờ",
                    "Huyện Củ Chi"))));
    locationService.saveLocation(
        new Location(
            null,
            "Hà Nội",
            new ArrayList<>(
                List.of(
                    "Quận Ba Đình",
                    "Quận Bắc Từ Liêm",
                    "Quận Cầu Giấy",
                    "Quận Đống Đa",
                    "Quận Hà Đông",
                    "Quận Hai Bà Trưng",
                    "Quận Hoàn Kiếm",
                    "Quận Hoàng Mai",
                    "Quận Long Biên",
                    "Quận Nam Từ Liêm",
                    "Quận Tây Hồ",
                    "Quận Thanh Xuân",
                    "Huyện Ba Vì",
                    "Huyện Chương Mỹ",
                    "Huyện Đan Phượng",
                    "Huyện Đông Anh",
                    "Huyện Gia Lâm",
                    "Huyện Hoài Đức",
                    "Huyện Mê Linh",
                    "Huyện Mỹ Đức",
                    "Huyện Phú Xuyên",
                    "Huyện Phúc Thọ",
                    "Huyện Quốc Oai",
                    "Huyện Sóc Sơn",
                    "Huyện Thạch Thất",
                    "Huyện Thanh Oai",
                    "Huyện Thanh Trì",
                    "Huyện Thường Tín",
                    "Huyện Ứng Hòa",
                    "Thị xã Sơn Tây"))));
    locationService.saveLocation(
        new Location(
            null,
            "Cần Thơ",
            new ArrayList<>(
                List.of(
                    "Quận Bình Thủy",
                    "Quận Cái Răng",
                    "Quận Ninh Kiều",
                    "Quận Ô Môn",
                    "Quận Thốt Nốt",
                    "Huyện Cờ Đỏ",
                    "Huyện Phong Điền",
                    "Huyện Thới Lai",
                    "Huyện Vĩnh Thạnh"))));
    locationService.saveLocation(
        new Location(
            null,
            "Đà Nẵng",
            new ArrayList<>(
                List.of(
                    "Quận Cam Le",
                    "Quận Hai Chau",
                    "Quận Hoa Vang",
                    "Quận Lien Chieu",
                    "Quận Ngu Hanh",
                    "Quận Son Tra",
                    "Quận Thanh Khe"))));
    locationService.saveLocation(
        new Location(
            null,
            "Hải Phòng",
            new ArrayList<>(
                List.of(
                    "Quận Dương Kinh",
                    "Quận Đồ Sơn",
                    "Quận Hải An",
                    "Quận Kiến An",
                    "Quận Hồng Bàng",
                    "Quận Ngô Quyền",
                    "Quận Lê Chân",
                    "Huyện An Dương",
                    "Huyện An Lão",
                    "Huyện Bạch Long Vĩ",
                    "Huyện Cát Hải",
                    "Huyện Kiến Thụy",
                    "Huyện Tiên Lãng",
                    "Huyện Vĩnh Bảo",
                    "Huyện Thủy Nguyên"))));
  }

  private AuthUser genAuthStudent(String username, String password, HashSet<Role> roles) {
    return new AuthUser(null, username, password, roles, this.genStudentMetadata());
  }

  private AuthUser genAuthTutor(String username, String password, HashSet<Role> roles) {
    return new AuthUser(null, username, password, roles, this.genTutorMetadata());
  }

  private UserMetadata genStudentMetadata() {
    return new UserMetadata(
        new FullName(faker.name().firstName(), faker.name().lastName()),
        faker.demographic().sex(),
        faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(),
        getRandomAddress(1).get(0),
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
        faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(),
        getRandomAddress(1).get(0),
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
    List<Subject> subjects = getRandomSubject(2);
    List<Grade> grades = subjects.get(0).getGrades();
    int start = faker.random().nextInt(grades.size() - 5);
    int end = faker.random().nextInt(start, grades.size());

    return new Tutor(
        faker.job().title(),
        faker.university().name(),
        faker.company().industry(),
        faker.options().option("Bachelor", "Engineering", "Master", "High school"),
        faker.random().nextInt(1990, 2020),
        new ArrayList<>(getRandomAddress(2)),
        new HashSet<>(grades.subList(start, end)),
        new HashSet<>(subjects),
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
        getRandomAddress(1).get(0),
        new ArrayList<>(List.of(faker.internet().emailAddress())),
        new ArrayList<>(List.of(faker.phoneNumber().phoneNumber())),
        faker.lorem().paragraph());
  }
}
