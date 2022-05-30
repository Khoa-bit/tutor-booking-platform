package com.example.baked.repo;

import com.example.baked.model.Subject;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SubjectRepo extends MongoRepository<Subject, String> {}
