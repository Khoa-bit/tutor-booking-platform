package com.example.baked.repo;

import com.example.baked.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TutorRepo extends MongoRepository<Student, String> {}
