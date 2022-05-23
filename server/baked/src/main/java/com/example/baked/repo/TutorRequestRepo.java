package com.example.baked.repo;

import com.example.baked.model.RequestFromTutor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TutorRequestRepo extends MongoRepository<RequestFromTutor, String> {}
