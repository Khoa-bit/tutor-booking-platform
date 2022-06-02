package com.example.baked.repo;

import com.example.baked.model.StudentRequest;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRequestRepo extends MongoRepository<StudentRequest, String> {
  List<StudentRequest> findAllByRequestClass_Tutor_Id(String tutorId);
}
