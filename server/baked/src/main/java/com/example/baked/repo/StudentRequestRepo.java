package com.example.baked.repo;

import com.example.baked.model.StudentRequest;
import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface StudentRequestRepo extends MongoRepository<StudentRequest, String> {
  @Query(value = "{_id: ?0}")
  Optional<StudentRequest> findStudentRequestById(String id);

  @Query(value = "{'requestClass.tutor': ?0}")
  List<StudentRequest> findAllByRequestClass_Tutor_Id(ObjectId tutorId);
}
