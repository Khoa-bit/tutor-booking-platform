package com.example.baked.service;

import com.example.baked.controller.error.BadRequestException;
import com.example.baked.controller.error.NotFoundException;
import com.example.baked.model.AuthUser;
import com.example.baked.model.Class;
import com.example.baked.model.Period;
import com.example.baked.model.StudentRequest;
import com.example.baked.model.TutorRequest;
import com.example.baked.repo.ClassRepo;
import com.example.baked.repo.StudentRequestRepo;
import com.example.baked.repo.TutorRequestRepo;
import com.example.baked.repo.UserRepo;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClassRequestService {
  private final StudentRequestRepo studentRequestRepo;
  private final TutorRequestRepo tutorRequestRepo;
  private final ClassRepo classRepo;
  private final UserRepo userRepo;
  private final MongoTemplate mongoTemplate;

  public StudentRequest saveStudentRequest(StudentRequest studentRequest) {
    log.info(
        "Saving new StudentRequest from {} to {}",
        studentRequest.getRequestClass().getStudent().getUsername(),
        studentRequest.getRequestClass().getTutor().getUsername());
    return studentRequestRepo.save(studentRequest);
  }

  public TutorRequest saveTutorRequest(TutorRequest tutorRequest) {
    validateRequestingTutors(tutorRequest);
    log.info(
        "Saving new TutorRequest from {} to {}",
        tutorRequest.getRequestingTutors().stream().map(AuthUser::getUsername).toList(),
        tutorRequest.getRequestClass().getStudent().getUsername());
    return tutorRequestRepo.save(tutorRequest);
  }

  public Optional<StudentRequest> overrideStudentRequest(StudentRequest studentRequest) {
    if (studentRequest.getId() == null || studentRequestRepo.existsById(studentRequest.getId())) {
      return Optional.empty();
    } else {
      log.info("Overriding StudentRequest {}", studentRequest.getId());
      return Optional.of(studentRequestRepo.save(studentRequest));
    }
  }

  public Optional<TutorRequest> overrideTutorRequest(TutorRequest tutorRequest) {
    if (tutorRequest.getId() == null || tutorRequestRepo.existsById(tutorRequest.getId())) {
      return Optional.empty();
    } else {
      validateRequestingTutors(tutorRequest);
      log.info("Overriding TutorRequest {}", tutorRequest.getId());
      return Optional.of(tutorRequestRepo.save(tutorRequest));
    }
  }

  private void validateRequestingTutors(TutorRequest tutorRequest) {
    List<AuthUser> tutors =
        mongoTemplate.find(
            Query.query(
                Criteria.where("_id")
                    .in(tutorRequest.getRequestingTutors().stream().map(AuthUser::getId).toList())),
            AuthUser.class);

    // Tutor without colliding occupiedPeriods with requested class.
    List<AuthUser> validTutors =
        tutors.stream()
            .filter(
                tutor ->
                    checkPeriodCollision(
                            tutor.getUserMetadata().getTutor().getOccupiedPeriods(),
                            tutorRequest.getRequestClass().getPeriods())
                        .isEmpty())
            .toList();

    tutorRequest.setRequestingTutors(validTutors);
  }

  public List<StudentRequest> getAllStudentRequest() {
    log.info("Fetching all StudentRequests");
    return studentRequestRepo.findAll();
  }

  public List<TutorRequest> getAllTutorRequest() {
    log.info("Fetching all TutorRequests");
    return tutorRequestRepo.findAll();
  }

  public Optional<StudentRequest> getStudentRequestById(String id) {
    if (id == null) {
      return Optional.empty();
    }

    Optional<StudentRequest> studentRequest = studentRequestRepo.findById(id);
    if (studentRequest.isEmpty()) {
      return Optional.empty();
    } else {
      log.info("Fetching StudentRequest {}", id);
      return studentRequest;
    }
  }

  public Optional<TutorRequest> getTutorRequestById(String id) {
    if (id == null) {
      return Optional.empty();
    }

    Optional<TutorRequest> tutorRequest = tutorRequestRepo.findById(id);
    if (tutorRequest.isEmpty()) {
      return Optional.empty();
    } else {
      log.info("Fetching TutorRequest {}", id);
      return tutorRequest;
    }
  }

  public List<StudentRequest> getAllStudentRequestByTutorId(String tutorId) {
    if (tutorId == null) {
      throw new BadRequestException("Tutor's Id is null");
    }

    log.info("Fetching StudentRequest by Tutor's ID {}", tutorId);
    return studentRequestRepo.findAllByRequestClass_Tutor_Id(new ObjectId(tutorId));
  }

  public List<TutorRequest> getAllTutorRequestByStudentId(String studentId) {
    if (studentId == null) {
      throw new BadRequestException("Student's Id is null");
    }
    log.info("Fetching TutorRequest by Student's ID {}", studentId);
    return tutorRequestRepo.findAllByRequestClass_Student_Id(new ObjectId(studentId));
  }

  public List<TutorRequest> getAllTutorRequestByTutorId(String tutorId) {
    if (tutorId == null) {
      throw new BadRequestException("Tutor's Id is null");
    }
    log.info("Fetching TutorRequest by Tutor's ID {}", tutorId);
    return tutorRequestRepo.findAllByRequestClass_Tutor_Id(new ObjectId(tutorId));
  }

  public Class acceptStudentRequest(String id) {
    if (id == null) {
      throw new BadRequestException("StudentRequest's Id is null");
    }

    Optional<StudentRequest> acceptedStudentRequest = studentRequestRepo.findById(id);
    if (acceptedStudentRequest.isEmpty()) {
      throw new NotFoundException("Invalid StudentRequest's Id");
    } else {
      Class acceptedClass = acceptedStudentRequest.get().getRequestClass();
      List<Period> acceptedPeriods = acceptedClass.getPeriods();
      List<StudentRequest> otherStudentRequests =
          getAllStudentRequestByTutorId(acceptedClass.getTutor().getId());
      List<StudentRequest> invalidRequests =
          otherStudentRequests.stream()
              .filter(
                  studentRequest ->
                      studentRequest.getRequestClass().getPeriods().stream()
                          .anyMatch(acceptedPeriods::contains))
              .toList();

      // These invalid requests should be removed.
      studentRequestRepo.deleteAll(invalidRequests);
      // Transform to Class and remove from requests.
      AuthUser acceptedTutor = acceptedClass.getTutor();
      acceptedTutor
          .getUserMetadata()
          .getTutor()
          .getOccupiedPeriods()
          .addAll(acceptedClass.getPeriods());
      userRepo.save(acceptedTutor);
      return classRepo.save(acceptedClass);
    }
  }

  public Class acceptTutorRequest(String requestId, String tutorId) {
    if (requestId == null || tutorId == null) {
      throw new BadRequestException("TutorRequest's Id or Tutor's Id is null");
    }

    Optional<TutorRequest> acceptedTutorRequest =
        tutorRequestRepo.findByIdAndRequestingTutorId(
            new ObjectId(requestId), new ObjectId(tutorId));
    if (acceptedTutorRequest.isEmpty()) {
      throw new NotFoundException("Invalid TutorRequest's Id or Tutor's Id");
    } else {
      Class acceptedClass = acceptedTutorRequest.get().getRequestClass();
      List<Period> acceptedPeriods = acceptedClass.getPeriods();
      AuthUser acceptedTutor =
          acceptedTutorRequest.get().getRequestingTutors().stream()
              .filter(authUser -> authUser.getId().equals(tutorId))
              .findAny()
              .orElseThrow(() -> new NotFoundException("Invalid TutorRequest's Id"));
      // Transform to Class and remove from requests
      tutorRequestRepo.delete(acceptedTutorRequest.get());
      acceptedTutor.getUserMetadata().getTutor().getOccupiedPeriods().addAll(acceptedPeriods);
      acceptedClass.setTutor(acceptedTutor);
      userRepo.save(acceptedTutor);

      // Find other requests that collide with accept tutor occupied periods
      List<TutorRequest> otherTutorRequest = getAllTutorRequestByTutorId(tutorId);
      List<TutorRequest> invalidRequests =
          otherTutorRequest.stream()
              .filter(
                  tutorRequest ->
                      tutorRequest.getRequestClass().getPeriods().stream()
                          .anyMatch(acceptedPeriods::contains))
              .toList();

      for (TutorRequest invalidRequest : invalidRequests) {
        invalidRequest.getRequestingTutors().remove(acceptedTutor);
      }
      tutorRequestRepo.saveAll(invalidRequests);

      return classRepo.save(acceptedClass);
    }
  }

  private List<Period> checkPeriodCollision(List<Period> periods1, List<Period> periods2) {
    return periods1.stream()
        .filter(period1 -> periods2.stream().anyMatch(period1::equals))
        .toList();
  }
}
