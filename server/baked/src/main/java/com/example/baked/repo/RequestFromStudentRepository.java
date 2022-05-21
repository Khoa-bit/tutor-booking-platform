package com.example.baked.repo;

import com.example.baked.model.RequestFromStudent;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface RequestFromStudentRepository extends MongoRepository<RequestFromStudent, String> {
  @Query(value = "{'tutor_id': ?0}")
  public List<RequestFromStudent> getRequestByTutorID(String id);
}
