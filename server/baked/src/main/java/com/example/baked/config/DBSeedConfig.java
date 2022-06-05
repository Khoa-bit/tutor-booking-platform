package com.example.baked.config;

import com.example.baked.model.Address;
import com.example.baked.model.AssignedSubject;
import com.example.baked.model.AuthUser;
import com.example.baked.model.Class;
import com.example.baked.model.FullName;
import com.example.baked.model.Grade;
import com.example.baked.model.Location;
import com.example.baked.model.Period;
import com.example.baked.model.Relative;
import com.example.baked.model.Role;
import com.example.baked.model.Student;
import com.example.baked.model.StudentRequest;
import com.example.baked.model.Subject;
import com.example.baked.model.SubjectName;
import com.example.baked.model.TeachingSubject;
import com.example.baked.model.Tutor;
import com.example.baked.model.TutorRequest;
import com.example.baked.model.UserMetadata;
import com.example.baked.repo.ClassRepo;
import com.example.baked.repo.LocationRepo;
import com.example.baked.repo.PeriodRepo;
import com.example.baked.repo.StudentRequestRepo;
import com.example.baked.repo.SubjectRepo;
import com.example.baked.repo.TutorRequestRepo;
import com.example.baked.repo.UserRepo;
import com.example.baked.service.ClassRequestService;
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
  private final ClassRepo classRepo;
  private final StudentRequestRepo studentRequestRepo;
  private final TutorRequestRepo tutorRequestRepo;
  private final ClassRequestService classRequestService;
  private final MongoTemplate mongoTemplate;

  @Bean
  CommandLineRunner seedRunner() {
    return args -> {
      if (args.length == 0) {
        return;
      }
      for (String arg : args) {
        if (!arg.equals("clearDB")) {
          return;
        }
      }

      classRepo.deleteAll();
      studentRequestRepo.deleteAll();
      tutorRequestRepo.deleteAll();
      userRepo.deleteAll();
      locationRepo.deleteAll();
      subjectRepo.deleteAll();
      periodRepo.deleteAll();

      // TODO: Generate Classes and Class Requests for both Student and Tutor
      //       using Periods and Subjects References
      List<Period> periods = this.genPeriods();
      List<Location> locations = this.genLocations(locationService);
      List<Subject> subjects = this.genSubjects(subjectRepo);

      AuthUser student1 =
          userService.saveAuthUser(
              this.genAuthStudent(
                  "alicesmith@student.hometutor",
                  "321",
                  new HashSet<>(List.of(Role.ROLE_STUDENT)),
                  periods,
                  locations,
                  subjects));
      AuthUser student2 =
          userService.saveAuthUser(
              this.genAuthStudent(
                  "bobsmith@student.hometutor",
                  "123",
                  new HashSet<>(List.of(Role.ROLE_STUDENT)),
                  periods,
                  locations,
                  subjects));

      AuthUser tutor1 =
          userService.saveAuthUser(
              this.genAuthTutor(
                  "liamthomson@tutor.hometutor",
                  "321",
                  new HashSet<>(List.of(Role.ROLE_TUTOR)),
                  periods,
                  locations,
                  subjects));
      AuthUser tutor2 =
          userService.saveAuthUser(
              this.genAuthTutor(
                  "olivathomson@tutor.hometutor",
                  "123",
                  new HashSet<>(List.of(Role.ROLE_TUTOR)),
                  periods,
                  locations,
                  subjects));

      List<StudentRequest> studentRequests = genStudentRequest(student1, student2, tutor1);
      List<TutorRequest> tutorRequests =
          genTutorRequest(locations, subjects, periods, student2, tutor1, tutor2);

      System.out.println(
          "==================================================================================");

      //      classRequestService.acceptStudentRequest(studentRequests.get(0).getId());
      classRequestService.acceptTutorRequest(tutorRequests.get(0).getId(), tutor2.getId());

      System.out.println(
          "==================================================================================");
    };
  }

  private List<TutorRequest> genTutorRequest(
      List<Location> locations,
      List<Subject> subjects,
      List<Period> periods,
      AuthUser student2,
      AuthUser tutor1,
      AuthUser tutor2) {
    int randSubject = faker.random().nextInt(subjects.size());
    int randGrade = faker.random().nextInt(subjects.get(randSubject).getGrades().size());
    List<TutorRequest> tutorRequests = new ArrayList<>();
    tutorRequests.add(
        new TutorRequest(
            null,
            List.of(tutor1, tutor2),
            new Class(
                null,
                null,
                student2,
                new AssignedSubject(
                    subjects.get(randSubject).getName(),
                    subjects.get(randSubject).getGrades().get(randGrade)),
                getRandomAddress(1, locations, true).get(0),
                500000,
                "Professional",
                new ArrayList<>(periods.subList(0, 2)))));

    tutorRequests.add(
        new TutorRequest(
            null,
            List.of(tutor1, tutor2),
            new Class(
                null,
                null,
                student2,
                new AssignedSubject(
                    subjects.get(randSubject).getName(),
                    subjects.get(randSubject).getGrades().get(randGrade)),
                getRandomAddress(1, locations, true).get(0),
                500000,
                "Professional",
                new ArrayList<>(periods.subList(1, 3)))));
    // return tutorRequestRepo.saveAll(tutorRequests);
    return tutorRequests.stream().map(classRequestService::saveTutorRequest).toList();
  }

  private List<StudentRequest> genStudentRequest(
      AuthUser student1, AuthUser student2, AuthUser tutor1) {
    Address tutor1Address = tutor1.getUserMetadata().getTutor().getAddresses().get(0);
    List<StudentRequest> studentRequests = new ArrayList<>();
    studentRequests.add(
        new StudentRequest(
            null,
            new Class(
                null,
                tutor1,
                student1,
                new AssignedSubject(
                    tutor1.getUserMetadata().getTutor().getTeachingSubject().get(0).getName(),
                    tutor1
                        .getUserMetadata()
                        .getTutor()
                        .getTeachingSubject()
                        .get(0)
                        .getGrades()
                        .get(0)),
                new Address(
                    tutor1Address.getProvince_city(),
                    tutor1Address.getWard_district(),
                    faker.address().streetAddress()),
                30000,
                "Hard Working",
                new ArrayList<>(
                    List.of(
                        tutor1.getUserMetadata().getTutor().getPeriods().get(0),
                        tutor1.getUserMetadata().getTutor().getPeriods().get(1))))));

    studentRequests.add(
        new StudentRequest(
            null,
            new Class(
                null,
                tutor1,
                student2,
                new AssignedSubject(
                    tutor1.getUserMetadata().getTutor().getTeachingSubject().get(1).getName(),
                    tutor1
                        .getUserMetadata()
                        .getTutor()
                        .getTeachingSubject()
                        .get(1)
                        .getGrades()
                        .get(1)),
                new Address(
                    tutor1Address.getProvince_city(),
                    tutor1Address.getWard_district(),
                    faker.address().streetAddress()),
                1000000,
                "Friendly",
                new ArrayList<>(
                    List.of(
                        tutor1.getUserMetadata().getTutor().getPeriods().get(2),
                        tutor1.getUserMetadata().getTutor().getPeriods().get(1))))));

    studentRequests.add(
        new StudentRequest(
            null,
            new Class(
                null,
                tutor1,
                student2,
                new AssignedSubject(
                    tutor1.getUserMetadata().getTutor().getTeachingSubject().get(1).getName(),
                    tutor1
                        .getUserMetadata()
                        .getTutor()
                        .getTeachingSubject()
                        .get(1)
                        .getGrades()
                        .get(1)),
                new Address(
                    tutor1Address.getProvince_city(),
                    tutor1Address.getWard_district(),
                    faker.address().streetAddress()),
                1000000,
                "Friendly",
                new ArrayList<>(
                    List.of(
                        tutor1.getUserMetadata().getTutor().getPeriods().get(2),
                        tutor1.getUserMetadata().getTutor().getPeriods().get(3))))));
    return studentRequestRepo.saveAll(studentRequests);
  }

  private List<Period> genPeriods() {
    LocalDateTime start = LocalDateTime.of(2000, 1, 1, 19, 0);
    ArrayList<Period> periods = new ArrayList<>();
    for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
      periods.add(new Period(dayOfWeek, start, start.plusHours(1).plusMinutes(30)));
    }
    return periodRepo.saveAll(periods);
  }

  private List<Address> getRandomAddress(
      long size, List<Location> locations, boolean hasStreetAddress) {
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

    Optional<Location> location =
        locations.stream()
            .filter(loc -> loc.getProvinceCity().equals("Hồ Chí Minh city"))
            .findAny();

    List<Address> addresses = new ArrayList<>();
    for (long i = 0; i < size; i++) {
      location.ifPresent(
          value ->
              addresses.add(
                  new Address(
                      value.getProvinceCity(),
                      faker.options().nextElement(value.getDistrict()),
                      hasStreetAddress ? faker.address().streetAddress() : null)));
    }
    return addresses;
  }

  private List<Subject> genSubjects(SubjectRepo subjectRepo) {
    ArrayList<Subject> subjects = new ArrayList<>();
    for (SubjectName name : SubjectName.values()) {
      subjects.add(new Subject(null, name, new ArrayList<>(List.of(Grade.values()))));
    }
    return subjectRepo.saveAll(subjects);
  }

  private List<Location> genLocations(LocationService locationService) {
    List<Location> locations = new ArrayList<>();
    locations.add(
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
    locations.add(
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
    locations.add(
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
    locations.add(
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
    locations.add(
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
    return locationRepo.saveAll(locations);
  }

  private AuthUser genAuthStudent(
      String username,
      String password,
      HashSet<Role> roles,
      List<Period> periods,
      List<Location> locations,
      List<Subject> subjects) {
    return new AuthUser(
        null, username, password, roles, this.genStudentMetadata(periods, locations, subjects));
  }

  private AuthUser genAuthTutor(
      String username,
      String password,
      HashSet<Role> roles,
      List<Period> periods,
      List<Location> locations,
      List<Subject> subjects) {
    return new AuthUser(
        null, username, password, roles, this.genTutorMetadata(periods, locations, subjects));
  }

  private UserMetadata genStudentMetadata(
      List<Period> periods, List<Location> locations, List<Subject> subjects) {
    return new UserMetadata(
        new FullName(faker.name().firstName(), faker.name().lastName()),
        faker.demographic().sex(),
        faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(),
        getRandomAddress(1, locations, true).get(0),
        new ArrayList<>(List.of(faker.internet().emailAddress())),
        new ArrayList<>(List.of(faker.phoneNumber().phoneNumber())),
        faker.lorem().paragraph(),
        new ArrayList<>(List.of(genRelative(locations), genRelative(locations))),
        this.genRandomStudent(),
        null);
  }

  private UserMetadata genTutorMetadata(
      List<Period> periods, List<Location> locations, List<Subject> subjects) {
    return new UserMetadata(
        new FullName(faker.name().firstName(), faker.name().lastName()),
        faker.demographic().sex(),
        faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(),
        getRandomAddress(1, locations, true).get(0),
        new ArrayList<>(List.of(faker.internet().emailAddress())),
        new ArrayList<>(List.of(faker.phoneNumber().phoneNumber())),
        faker.lorem().paragraph(),
        new ArrayList<>(List.of(genRelative(locations), genRelative(locations))),
        null,
        this.genRandomTutor(periods, locations, subjects));
  }

  private Student genRandomStudent() {
    return new Student(new ArrayList<>());
  }

  private Tutor genRandomTutor(
      List<Period> periods, List<Location> locations, List<Subject> subjects) {
    int randInt = faker.random().nextInt(subjects.size() - 1);

    return new Tutor(
        faker.job().title(),
        faker.university().name(),
        faker.company().industry(),
        faker.options().option("Bachelor", "Engineering", "Master", "High school"),
        faker.random().nextInt(1990, 2020),
        new ArrayList<>(getRandomAddress(2, locations, false)),
        new ArrayList<>(
            List.of(
                genTeachingSubject(subjects.get(randInt)),
                genTeachingSubject(subjects.get((randInt + 4) % subjects.size())),
                genTeachingSubject(subjects.get((randInt + 2) % subjects.size())))),
        faker.random().nextInt(1, 20) * 100000,
        periods.subList(0, 4),
        new ArrayList<>());
  }

  private TeachingSubject genTeachingSubject(Subject subject) {
    return new TeachingSubject(subject.getName(), getRandomGrades(subject));
  }

  private List<Grade> getRandomGrades(Subject subject) {
    List<Grade> grades = subject.getGrades();
    int start = faker.random().nextInt(grades.size() - 5);
    int end = faker.random().nextInt(start + 3, grades.size());
    return subject.getGrades().subList(start, end);
  }

  private Relative genRelative(List<Location> locations) {
    return new Relative(
        faker.random().hex(4).toLowerCase(),
        faker.options().option("Mother", "Father", "Brother", "Sister"),
        new FullName(faker.name().firstName(), faker.name().lastName()),
        faker.demographic().sex(),
        faker.date().birthday(),
        getRandomAddress(1, locations, true).get(0),
        new ArrayList<>(List.of(faker.internet().emailAddress())),
        new ArrayList<>(List.of(faker.phoneNumber().phoneNumber())),
        faker.lorem().paragraph());
  }
}
