package com.example.baked.repository;

import java.util.List;

import com.example.baked.model.Student;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface StudentRepository extends MongoRepository<Student, String>{
    @Query(value = "{'student_id': ?0}")
    public Student getStudentByStudentID(String id);

    @Query(value = "{'username': ?0}")
    public Student getStudentByUsername(String username);

}
