package com.example.baked.repo;

import com.example.baked.model.Tutor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface TutorRepository extends MongoRepository<Tutor, String> {
  @Query(value = "{'tutor_id': ?0}")
  public Tutor getTutorByTutorID(String id);

  @Query(value = "{'username': ?0}")
  public Tutor getTutorByUsername(String username);
}
