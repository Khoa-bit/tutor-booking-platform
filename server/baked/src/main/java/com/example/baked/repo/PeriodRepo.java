package com.example.baked.repo;

import com.example.baked.model.Period;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface PeriodRepo extends MongoRepository<Period, String> {
  @Query(value = "{'tutor_id': ?0}")
  List<Period> getPeriodByTutorID(String id);

  @Query(value = "{'period_id': ?0}")
  Period getPeriodByPeriodID(String id);
}
