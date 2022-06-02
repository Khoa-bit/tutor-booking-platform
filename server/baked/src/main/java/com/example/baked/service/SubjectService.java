package com.example.baked.service;

import com.example.baked.model.Subject;
import com.example.baked.repo.SubjectRepo;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubjectService {
  private final SubjectRepo subjectRepo;

  public List<Subject> getAllSubjects() {
    log.info("Fetching all Subjects");
    return subjectRepo.findAll();
  }
}
