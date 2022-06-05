package com.example.baked.repo;

import com.example.baked.model.Period;
import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface PeriodRepo extends MongoRepository<Period, String> {
  @Query(value = "{'tutorId': ?0}")
  List<Period> getPeriodByTutorID(ObjectId id);

  Optional<Period> findByHash(int hash);
}
