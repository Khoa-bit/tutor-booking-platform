package com.example.baked.repo;

import com.example.baked.model.Tutor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TutorRepo extends MongoRepository<Tutor, String> {}
