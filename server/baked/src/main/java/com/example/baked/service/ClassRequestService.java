package com.example.baked.service;

import com.example.baked.controller.error.BadRequestException;
import com.example.baked.controller.error.NotFoundException;
import com.example.baked.model.StudentRequest;
import com.example.baked.model.TutorRequest;
import com.example.baked.repo.StudentRequestRepo;
import com.example.baked.repo.TutorRequestRepo;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClassRequestService {
  private final StudentRequestRepo studentRequestRepo;
  private final TutorRequestRepo tutorRequestRepo;

  public StudentRequest saveStudentRequest(StudentRequest studentRequest) {
    log.info(
        "Saving new StudentRequest from {} to {}",
        studentRequest.getRequestClass().getStudent().getUsername(),
        studentRequest.getRequestClass().getTutor().getUsername());
    return studentRequestRepo.save(studentRequest);
  }

  public TutorRequest saveTutorRequest(TutorRequest tutorRequest) {
    log.info(
        "Saving new TutorRequest from {} to {}",
        tutorRequest.getRequestClass().getTutor().getUsername(),
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
      log.info("Overriding TutorRequest {}", tutorRequest.getId());
      return Optional.of(tutorRequestRepo.save(tutorRequest));
    }
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
    return studentRequestRepo.findAllByRequestClass_Tutor_Id(tutorId);
  }

  public List<TutorRequest> getAllTutorRequestByStudentId(String studentId) {
    if (studentId == null) {
      throw new BadRequestException("Student's Id is null");
    }

    log.info("Fetching TutorRequest by Student's ID {}", studentId);
    return tutorRequestRepo.findAllByRequestClass_Student_Id(studentId);
  }

  public void acceptStudentRequest(String id) {
    if (id == null) {
      throw new BadRequestException("StudentRequest's Id is null");
    }

    Optional<StudentRequest> acceptedStudentRequest = studentRequestRepo.findById(id);
    if (acceptedStudentRequest.isEmpty()) {
      throw new NotFoundException("Invalid StudentRequest's Id");
    } else {
      List<StudentRequest> otherStudentRequests =
          getAllStudentRequestByTutorId(
              acceptedStudentRequest.get().getRequestClass().getTutor().getId());
      List<StudentRequest> invalidRequests =
          otherStudentRequests.stream()
              .filter(
                  studentRequest ->
                      studentRequest.getRequestClass().getPeriods().stream()
                          .anyMatch(
                              period ->
                                  acceptedStudentRequest
                                      .get()
                                      .getRequestClass()
                                      .getPeriods()
                                      .contains(period)))
              .toList();

      // Transform to Class and remove from requests
      log.info(acceptedStudentRequest.toString());
      // These invalid requests should be removed.
      log.info(invalidRequests.toString());
    }
  }

  public void acceptTutorRequest(String id) {
    if (id == null) {
      throw new BadRequestException("TutorRequest's Id is null");
    }

    Optional<TutorRequest> acceptedTutorRequest = tutorRequestRepo.findById(id);
    if (acceptedTutorRequest.isEmpty()) {
      throw new NotFoundException("Invalid TutorRequest's Id");
    } else {
      List<TutorRequest> invalidRequests =
          getAllTutorRequestByStudentId(
              acceptedTutorRequest.get().getRequestClass().getStudent().getId());

      // Transform to Class and remove from requests
      log.info(acceptedTutorRequest.toString());
      // These invalid requests should be removed.
      log.info(invalidRequests.toString());
    }
  }
}
