package com.example.baked.repo;

import com.example.baked.model.TutorRequest;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TutorRequestRepo extends MongoRepository<TutorRequest, String> {
  List<TutorRequest> findAllByRequestClass_Student_Id(String studentId);
}
