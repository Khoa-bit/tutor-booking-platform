package com.example.baked.repo;

import com.example.baked.model.Period;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface PeriodRepo extends MongoRepository<Period, String> {
  @Query(value = "{'tutorId': ?0}")
  List<Period> getPeriodByTutorID(String id);

  @Query(value = "{'_id': ?0}")
  Period getPeriodByPeriodID(String id);

  Optional<Period> findByHash(int hash);
}
