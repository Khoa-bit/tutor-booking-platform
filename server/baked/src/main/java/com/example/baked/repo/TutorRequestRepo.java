package com.example.baked.repo;

import com.example.baked.model.TutorRequest;
import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface TutorRequestRepo extends MongoRepository<TutorRequest, String> {
  @Query(value = "{'requestClass.student': ?0}")
  List<TutorRequest> findAllByRequestClass_Student_Id(ObjectId studentId);

  @Query(value = "{requestingTutors: ?0}")
  List<TutorRequest> findAllByRequestClass_Tutor_Id(ObjectId tutorId);

  @Query(value = "{_id: ?0, requestingTutors: ?1}")
  Optional<TutorRequest> findByIdAndRequestingTutorId(ObjectId requestId, ObjectId tutorId);
}
