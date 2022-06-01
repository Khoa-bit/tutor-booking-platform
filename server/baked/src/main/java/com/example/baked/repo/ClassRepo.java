package com.example.baked.repo;

import com.example.baked.model.Class;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ClassRepo extends MongoRepository<Class, String> {
  @Query(value = "{'class_id': ?0}")
  Class getClassByClassID(String id);

  @Query(value = "{'student_id': ?0}")
  List<Class> getClassByStudentID(String id);

  @Query(value = "{'tutor_id': ?0}")
  List<Class> getClassByTutorID(String id);
}
