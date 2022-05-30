package com.example.baked.repo;

import com.example.baked.model.Location;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LocationRepo extends MongoRepository<Location, String> {
  Optional<Location> findByProvinceCity(String provinceCity);
}
